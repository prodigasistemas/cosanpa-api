package br.gov.pa.cosanpa.api.service.faturamento.conta

import br.gov.pa.cosanpa.api.dominio.faturamento.conta.ComunicadoEmitirConta
import br.gov.pa.cosanpa.api.dto.faturamento.conta.ContaImpostosDeduzidosDTO
import br.gov.pa.cosanpa.api.repository.faturamento.conta.ContaRepository
import br.gov.pa.cosanpa.api.util.ConstantesSistema
import br.gov.pa.cosanpa.api.view.faturamento.conta.ContaImpostosDeduzidosView
import br.gov.pa.cosanpa.api.view.faturamento.conta.ContaViewMapper
import org.springframework.stereotype.Service

@Service
class ContaService(
    private val repository: ContaRepository,
    private val viewMapper: ContaViewMapper
) {

    fun obterContaPreFaturadaPorImovelId(idImovel: Int, anoMesReferencia: Int) =
        repository.obterContaPreFaturada(idImovel, anoMesReferencia)

    fun possuiComunicadoNaoEmitidoAlteracaoCadastral(idImovel: Int, referencia: Int): Boolean {
        return repository.quantidadeComunicadoEmitido(
            idImovel = idImovel,
            referencia = referencia,
            tipoComunicado = ComunicadoEmitirConta.ALTERACAO_CADASTRAL,
            indicadorEmitido = ConstantesSistema.NAO
        ).toInt() > 0
    }

    fun obterMensagemContaView(
        referencia: Int,
        idFaturamentoGrupo: Int?,
        idGerenciaRegional: Int?,
        idLocalidade: Int?,
        idSetorComercial: Int?,
        idQuadra: Int?
    ): String? {
        val mensagem = repository.obterContaMensagem3Partes(
            referencia = referencia,
            idFaturamentoGrupo = null,
            idGerenciaRegional = idGerenciaRegional,
            idLocalidade = idLocalidade,
            idSetorComercial = idSetorComercial,
            idQuadra = idQuadra
        ) ?: repository.obterContaMensagem3Partes(
            referencia = referencia,
            idFaturamentoGrupo = idFaturamentoGrupo,
            idGerenciaRegional = null,
            idLocalidade = null,
            idSetorComercial = null,
            idQuadra = null
        ) ?: repository.obterContaMensagem3Partes(
            referencia = referencia,
            idFaturamentoGrupo = null,
            idGerenciaRegional = null,
            idLocalidade = null,
            idSetorComercial = null,
            idQuadra = null
        )
        return mensagem?.let { viewMapper.mapMensagemConta(it) }
    }

    fun obterColecaoContaImpostoDeduzidos(idConta: Int) = repository.obterDadosContaImpostosDeduzidos(idConta)

    fun obterContaImpostosDeduzidosView(contaImpostosDeduzidosDTO: ContaImpostosDeduzidosDTO): ContaImpostosDeduzidosView {
        return viewMapper.mapContaImpostosDeduzidos(contaImpostosDeduzidosDTO)
    }

    fun obterImpostoTipoView(idImpostoTipo: Int) = viewMapper.map(repository.obterDadosImpostoTipo(idImpostoTipo))
}