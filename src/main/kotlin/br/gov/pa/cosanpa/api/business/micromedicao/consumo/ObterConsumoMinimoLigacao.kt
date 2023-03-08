package br.gov.pa.cosanpa.api.business.micromedicao.consumo

import br.gov.pa.cosanpa.api.extensions.util.isNullOuVazio
import br.gov.pa.cosanpa.api.service.cadastro.imovel.ImovelService
import br.gov.pa.cosanpa.api.service.faturamento.consumo.ConsumoTarifaCategoriaService
import br.gov.pa.cosanpa.api.service.faturamento.consumo.ConsumoTarifaVigenciaService
import org.springframework.stereotype.Component



@Component
class ObterConsumoMinimoLigacao(
    private val imovelService: ImovelService,
    private val consumoTarifaVigenciaService: ConsumoTarifaVigenciaService,
    private val consumoTarifaCategoriaService: ConsumoTarifaCategoriaService
) {
    fun obter(idImovel: Int): Int {
        val imovelDTO = imovelService.obterConsumoTarifaImovel(idImovel)
        val consumoTarifaVigenciaDTO = consumoTarifaVigenciaService.obterVigencias(imovelDTO.consumoTarifa)
        val categorias = imovelService.obterCategorias(idImovel)

        var consumoMinimoLigacao = 0

        categorias.forEach { categoria ->
            val consumoMinimoTarifaCategoria = consumoTarifaCategoriaService.obterNumeroConsumoMinimoTarifaCategoria(
                consumoTarifaVigenciaDTO.id,
                categoria.id
            )

            if (!categoria.fatorEconomias.isNullOuVazio()) {
                consumoMinimoLigacao += consumoMinimoTarifaCategoria * categoria.fatorEconomias!!
            } else {
                categoria.quantidadeEconomias?.let {
                    consumoMinimoLigacao += consumoMinimoTarifaCategoria * it.toInt()
                }
            }
        }
        return consumoMinimoLigacao
    }
}