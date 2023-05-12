package br.gov.pa.cosanpa.api.service.cadastro.imovel

import br.gov.pa.cosanpa.api.business.faturamento.gerardados.GerarDadosBO
import br.gov.pa.cosanpa.api.extensions.cadastro.imovel.formatarInscricao
import br.gov.pa.cosanpa.api.repository.cadastro.imovel.ImovelRepository
import br.gov.pa.cosanpa.api.service.arrecadacao.banco.BancoService
import br.gov.pa.cosanpa.api.service.arrecadacao.debitoautomatico.DebitoAutomaticoService
import br.gov.pa.cosanpa.api.service.atendimentopublico.LigacaoService
import br.gov.pa.cosanpa.api.service.cadastro.cliente.ClienteService
import br.gov.pa.cosanpa.api.service.cadastro.endereco.EnderecoService
import br.gov.pa.cosanpa.api.service.cadastro.imovel.localidade.LocalidadeService
import br.gov.pa.cosanpa.api.service.faturamento.consumo.ConsumoTarifaService
import br.gov.pa.cosanpa.api.view.GerarDadosView
import br.gov.pa.cosanpa.api.view.View
import br.gov.pa.cosanpa.api.view.cadastro.imovel.ImovelViewMapper
import org.json.JSONObject
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class ImovelService(
    private val repository: ImovelRepository,
    private val viewMapper: ImovelViewMapper,
    private val enderecoService: EnderecoService,
    private val localidadeService: LocalidadeService,
    private val categoriaService: CategoriaService,
    private val clienteService: ClienteService,
    private val ligacaoService: LigacaoService,
    private val debitoAutomaticoService: DebitoAutomaticoService,
    private val bancoService: BancoService,
    private val consumoTarifaService: ConsumoTarifaService
) {

    fun obterConsumoTarifaImovel(idImovel: Int) = repository.obterConsumoTarifa(idImovel)
    
    fun obterDadosCategoriasPorImovel(idImovel: Int) = repository.obterDadosCategoriasPorImovel(idImovel)

    fun gerarDadosImoveisPorRota(idRota: Int) : ResponseEntity<List<GerarDadosView>> {
        val idsImoveis = obterIdsImovelPorRota(idRota)

        return gerarDadosImoveis(idsImoveis)
    }

    private fun gerarDadosImoveis(idsImoveis: List<Int>): ResponseEntity<List<GerarDadosView>> {
        val gerarDadosLista = mutableListOf<GerarDadosView>()

        idsImoveis.forEach { idImovel ->
            val gerarDadosView = GerarDadosBO(
                imovelService = this,
                localidadeService = localidadeService,
                categoriaService = categoriaService,
                enderecoService = enderecoService,
                clienteService = clienteService,
                ligacaoService = ligacaoService,
                debitoAutomaticoService = debitoAutomaticoService,
                bancoService = bancoService
            ).gerarPorImovel(idImovel)

            gerarDadosLista.add(gerarDadosView)
        }

        return if (gerarDadosLista.isEmpty()) {
            ResponseEntity.noContent().build()
        } else {
            ResponseEntity.ok().body(gerarDadosLista)
        }
    }

    fun obterIdsImovelPorRota(idRota: Int) = repository.obterIdsImoveis(idRota)

    fun obterDadosImovel(idImovel: Int) = repository.obterDadosImovelGerarDados(idImovel)

    fun obterImovelView(idImovel: Int) = obterDadosImovel(idImovel)?.let { viewMapper.map(it) }

    fun obterDadosClienteImovel(idImovel: Int) = repository.obterClienteImoveis(idImovel)

    fun obterDadosInscricao(idImovel: Int) = repository.obterDadosInscricao(idImovel).formatarInscricao()

    fun obterDadosImovelContaEnvio(it: Int) = repository.obterDadosImovelContaEnvio(it)

    fun obterImovelContaEnvioView(idImovel: Int): View? {
        val imovelDto = obterDadosImovel(idImovel)
        imovelDto?.idImovelContaEnvio?.let {
            return viewMapper.mapDtoGenerico(obterDadosImovelContaEnvio(it))
        }
        return null
    }

    fun obterImoveisEnderecoPorRota(idRota: Int): ResponseEntity<MutableMap<String, Any>> {
        val retorno : MutableMap<String, Any> = mutableMapOf()
        val listaDados = mutableListOf<MutableMap<String, Any>>()

        val idsImoveis = repository.obterIdsImoveis(idRota)
        idsImoveis.forEach { idImovel ->
            obterDadosImovel(idImovel)?.let { imovelDTO ->
                val body = LinkedHashMap<String, Any>()

                body["rotaId"] = idRota
                body["matricula"] = imovelDTO.id ?: 0
                body["endereco"] = imovelDTO.id?.let { enderecoService.obterEnderecoFormatadoImovel(it) } ?: ""

                listaDados.add(body)
            }
        }

        retorno["imoveis"] = listaDados

        return if (retorno.isEmpty()) {
            ResponseEntity.noContent().build()
        } else {
            ResponseEntity.ok().body(retorno)
        }
    }
}
