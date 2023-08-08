package br.gov.pa.cosanpa.api.business.micromedicao.consumo

import br.gov.pa.cosanpa.api.service.cadastro.imovel.CategoriaService
import br.gov.pa.cosanpa.api.service.cadastro.imovel.ImovelService
import br.gov.pa.cosanpa.api.service.faturamento.consumo.ConsumoTarifaService
import org.springframework.stereotype.Component


@Component
class ConsumoMinimoLigacaoBO(
    private val imovelService: ImovelService,
    private val categoriaService: CategoriaService,
    private val consumoTarifaService: ConsumoTarifaService
) {
    fun obter(idImovel: Int): Int {
        val idConsumoTarifa = imovelService.obterConsumoTarifaImovel(idImovel)
        val categorias = categoriaService.obterDadosCategorias(idImovel)
        val consumoTarifaVigenciaDTO = consumoTarifaService.obterDataTarifaVigenciaCorrente(idConsumoTarifa)
        var consumoMinimoLigacao = 0

        categorias.forEach { categoria ->
            val consumoMinimoTarifaCategoria = consumoTarifaService.obterNumeroConsumoMinimoTarifaCategoria(
                consumoTarifaVigenciaDTO.id!!,
                categoria.id!!
            )
            consumoMinimoLigacao += consumoMinimoTarifaCategoria * categoria.quantidadeEconomias!!.toInt()
        }

        return consumoMinimoLigacao
    }
}