package br.gov.pa.cosanpa.api.business.faturamento.impressaosimultanea

import br.gov.pa.cosanpa.api.dominio.faturamento.atividade.FaturamentoAtividade
import br.gov.pa.cosanpa.api.dto.Dto
import br.gov.pa.cosanpa.api.dto.faturamento.consumo.ConsumoTarifaCategoriaDTO
import br.gov.pa.cosanpa.api.service.cadastro.SistemaParametrosService
import br.gov.pa.cosanpa.api.service.cadastro.imovel.CategoriaService
import br.gov.pa.cosanpa.api.service.cadastro.imovel.ImovelService
import br.gov.pa.cosanpa.api.service.faturamento.atividade.FaturamentoAtividadeService
import br.gov.pa.cosanpa.api.service.faturamento.consumo.ConsumoTarifaService
import org.springframework.stereotype.Component
import java.time.LocalDate

@Component
class DadosConsumoTarifaBO(
    private val sistemaParametrosService: SistemaParametrosService,
    private val consumoTarifaService: ConsumoTarifaService,
    private val faturamentoAtividadeService: FaturamentoAtividadeService,
    private val categoriaService: CategoriaService,
    private val imovelService: ImovelService
) {
    private var mapConsumoTarifaCategoria = mutableMapOf<String, Any>()
    private val sistemaParametros = sistemaParametrosService.obterParametrosDoSistema()

    fun obter(idGrupo: Int): MutableMap<String, Any> {
        val mapPorCategoria = mutableMapOf<String, Any>()
        categoriaService.obterColecaoIdDescricaoCategorias().forEach { categoriaDto ->
            categoriaDto.id?.let { idCategoria ->
                imovelService.obterColecaoIdConsumoTarifaEmUso().forEach { idConsumoTarifa ->
                    mapConsumoTarifaCategoria = mutableMapOf()
                    adicionarDadosConsumoTarifaCategoria(idGrupo, idCategoria, idConsumoTarifa)
                }
                categoriaDto.descricao?.let { mapPorCategoria[it] = mapConsumoTarifaCategoria }
            }
        }
        return mapPorCategoria
    }

    private fun adicionarDadosConsumoTarifaCategoria(idGrupo: Int, idCategoria: Int, idConsumoTarifa: Int) {
        val dataLeituraAnterior = obterDataLeituraAnterior(idGrupo)
        listaConsumoTarifaCategoria(
            dataLeituraAnterior,
            idCategoria,
            idConsumoTarifa
        ).forEach { consumoTarifaCategoriaDTO ->
            mapConsumoTarifaCategoria["consumoTarifaCategoria"] =
                consumoTarifaService.obterConsumoTarifaCategoriaView(consumoTarifaCategoriaDTO)

            consumoTarifaCategoriaDTO.id?.let {
                adicionarDadosConsumoTarifaFaixa(it)
            }
        }
    }

    private fun adicionarDadosConsumoTarifaFaixa(idConsumoTarifaCategoria: Int) {
        consumoTarifaService.obterColecaoConsumoTarifaFaixaPorCategoria(idConsumoTarifaCategoria).forEach { _ ->
            mapConsumoTarifaCategoria["consumoTarifaFaixa"] = consumoTarifaService.obterConsumoTarifaFaixaView(idConsumoTarifaCategoria)
        }
    }

    private fun listaConsumoTarifaCategoria(
        dataLeituraAnterior: LocalDate,
        idCategoria: Int,
        idConsumoTarifa: Int
    ): List<ConsumoTarifaCategoriaDTO> {
        var listaConsumoTarifaCategoria =  consumoTarifaService.obterDadosConsumoTarifaCategoriaProporcional(
            dataLeituraAnterior = dataLeituraAnterior,
            idCategoria = idCategoria,
            idConsumoTarifa = idConsumoTarifa
        )

        val dataTarifaVigenciaCorrente = consumoTarifaService.obterDataTarifaVigenciaCorrente(idConsumoTarifa).dataVigencia!!

        if (listaConsumoTarifaCategoria.isEmpty()) {
            listaConsumoTarifaCategoria = consumoTarifaService.obterDadosConsumoTarifaCategoriaPorDataVingente(
                dataVigencia = dataTarifaVigenciaCorrente,
                idCategoria = idCategoria,
                idConsumoTarifa = idConsumoTarifa
            )
        }

        return listaConsumoTarifaCategoria
    }

    private fun obterDataLeituraAnterior(idGrupo: Int) = faturamentoAtividadeService.obterDataPrevista(
        idFaturamentoGrupo = idGrupo,
        FaturamentoAtividade.GERAR_ARQUIVO_LEITURA,
        sistemaParametros.referenciaFaturamento
    )
}
