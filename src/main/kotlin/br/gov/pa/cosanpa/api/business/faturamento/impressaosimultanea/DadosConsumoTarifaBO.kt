package br.gov.pa.cosanpa.api.business.faturamento.impressaosimultanea

import br.gov.pa.cosanpa.api.dominio.faturamento.atividade.FaturamentoAtividade
import br.gov.pa.cosanpa.api.dto.faturamento.consumo.ConsumoTarifaCategoriaDTO
import br.gov.pa.cosanpa.api.service.cadastro.SistemaParametrosService
import br.gov.pa.cosanpa.api.service.cadastro.imovel.CategoriaService
import br.gov.pa.cosanpa.api.service.cadastro.imovel.ImovelService
import br.gov.pa.cosanpa.api.service.faturamento.atividade.FaturamentoAtividadeService
import br.gov.pa.cosanpa.api.service.faturamento.consumo.ConsumoTarifaService
import br.gov.pa.cosanpa.api.view.faturamento.consumo.ConsumoTarifaFaixaView
import org.springframework.stereotype.Component
import java.time.LocalDate

@Component
class DadosConsumoTarifaBO(
    sistemaParametrosService: SistemaParametrosService,
    private val consumoTarifaService: ConsumoTarifaService,
    private val faturamentoAtividadeService: FaturamentoAtividadeService,
    private val categoriaService: CategoriaService,
    private val imovelService: ImovelService
) {
    private val sistemaParametros = sistemaParametrosService.obterParametrosDoSistema()

    fun obter(idGrupo: Int): MutableMap<String, Any> {
        val mapPorCategoria = mutableMapOf<String, Any>()
        categoriaService.obterColecaoIdDescricaoCategorias().forEach { categoriaDto ->
            categoriaDto.id?.let { idCategoria ->
                mapPorCategoria[categoriaDto.descricao!!] = imovelService.obterColecaoIdConsumoTarifaEmUso().map { idConsumoTarifa ->
                        retornaDadosPorConsumoTarifa(idConsumoTarifa, idGrupo, idCategoria)
                    }
            }
        }
        return mapPorCategoria
    }

    private fun retornaDadosPorConsumoTarifa(
        idConsumoTarifa: Int,
        idGrupo: Int,
        idCategoria: Int
    ): MutableMap<String, Any> {
        val mapPorConsumoTarifa1: MutableMap<String, Any> = mutableMapOf()
        val consumoTarifaView = consumoTarifaService.obterDadosConsumoTarifaView(idConsumoTarifa)
        mapPorConsumoTarifa1[consumoTarifaView.descricao] = retornaColecaoDadosConsumoTarifaCategoria(idGrupo, idCategoria, idConsumoTarifa)
        return mapPorConsumoTarifa1
    }

    private fun retornaColecaoDadosConsumoTarifaCategoria(
        idGrupo: Int,
        idCategoria: Int,
        idConsumoTarifa: Int
    ): MutableList<MutableMap<String, Any>> {
        val dataLeituraAnterior = obterDataLeituraAnterior(idGrupo)
        val lista = mutableListOf<MutableMap<String, Any>>()
            listaConsumoTarifaCategoria(
            dataLeituraAnterior,
            idCategoria,
            idConsumoTarifa
        ).map { consumoTarifaCategoriaDTO ->
            val map = mutableMapOf<String, Any>()

            map["consumoTarifaCategoria"] = consumoTarifaService.obterConsumoTarifaCategoriaView(consumoTarifaCategoriaDTO)

            consumoTarifaCategoriaDTO.id?.let {
                map["consumoTarifaFaixa"] = retornaColecaoDadosConsumoTarifaFaixa(it)
            }
            lista += map
        }
        return lista
    }

    private fun retornaColecaoDadosConsumoTarifaFaixa(idConsumoTarifaCategoria: Int): MutableList<ConsumoTarifaFaixaView> {
        var consumosTarifaFaixa = mutableListOf<ConsumoTarifaFaixaView>()
        repeat(consumoTarifaService.obterColecaoConsumoTarifaFaixaPorCategoria(idConsumoTarifaCategoria).size) {
            consumosTarifaFaixa = mutableListOf()
            consumosTarifaFaixa += consumoTarifaService.obterConsumoTarifaFaixaView(idConsumoTarifaCategoria)
        }
        return consumosTarifaFaixa
    }

    private fun listaConsumoTarifaCategoria(
        dataLeituraAnterior: LocalDate,
        idCategoria: Int,
        idConsumoTarifa: Int
    ): List<ConsumoTarifaCategoriaDTO> {

        val listaConsumoTarifaCategoria = consumoTarifaService.obterDadosConsumoTarifaCategoriaProporcional(
            dataLeituraAnterior = dataLeituraAnterior,
            idCategoria = idCategoria,
            idConsumoTarifa = idConsumoTarifa
        ).toMutableList()

        listaConsumoTarifaCategoria += consumoTarifaService.obterDadosConsumoTarifaCategoriaPorDataVingente(
            dataVigencia = dataLeituraAnterior,
            idCategoria = idCategoria,
            idConsumoTarifa = idConsumoTarifa
        )

        return listaConsumoTarifaCategoria
    }

    private fun obterDataLeituraAnterior(idGrupo: Int) = faturamentoAtividadeService.obterDataPrevista(
        idFaturamentoGrupo = idGrupo,
        FaturamentoAtividade.GERAR_ARQUIVO_LEITURA,
        sistemaParametros.referenciaFaturamento
    )
}
