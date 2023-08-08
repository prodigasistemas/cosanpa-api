package br.gov.pa.cosanpa.api.service.micromedicao.consumo

import br.gov.pa.cosanpa.api.dominio.micromedicao.consumo.LigacaoTipo
import br.gov.pa.cosanpa.api.dto.micromedicao.consumo.ConsumoHistoricoDTO
import br.gov.pa.cosanpa.api.repository.micromedicao.consumohistorico.ConsumoHistoricoRepository
import br.gov.pa.cosanpa.api.view.micromedicao.consumo.ConsumoHistoricoViewMapper
import org.springframework.stereotype.Service

@Service
class ConsumoHistoricoService(
    private val repository: ConsumoHistoricoRepository,
    private val viewMapper: ConsumoHistoricoViewMapper
) {

    fun obterColecaoConsumosHistoricoEntreReferencias(
        idImovel: Int,
        idLigacao: Int,
        amReferenciaInicial: Int,
        amReferenciaFinal: Int
    ): List<ConsumoHistoricoDTO> =
        repository.obterConsumosEntreReferencias(idImovel, idLigacao, amReferenciaInicial, amReferenciaFinal)

    fun obterColecaoUltimosConsumosHistorico(
        idImovel: Int,
        idHidrometroInstalacaoHistorico: Int?
    ) = if (idHidrometroInstalacaoHistorico == null) {
        repository.obterColecaoUltimosConsumos(idImovel, LigacaoTipo.LIGACAO_AGUA)
    } else {
        repository.obterColecaoUltimosConsumos(idImovel, LigacaoTipo.LIGACAO_ESGOTO)
    }

    fun obterColecaoViewConsumosHistorico(dto: ConsumoHistoricoDTO) = viewMapper.mapConsumoHistorico(dto)

    fun obterConsumoAnormalidadeView(idConsumoAnormalidade: Int) =
        viewMapper.map(repository.obterDadosConsumoAnormalidade(idConsumoAnormalidade))

}
