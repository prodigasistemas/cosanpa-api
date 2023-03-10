package br.gov.pa.cosanpa.api.business.micromedicao.consumo

import br.gov.pa.cosanpa.api.dto.cadastro.imovel.CategoriaDTO
import br.gov.pa.cosanpa.api.dto.faturamento.consumo.ConsumoTarifaVigenciaDTO
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
        val categorias = imovelService.obterCategorias(idImovel)
        val consumoTarifaVigenciaDTO = consumoTarifaVigenciaService.obterTarifaVigenciaCorrente(imovelDTO.consumoTarifa!!)

        return calcularConsumoMinimoPorCategoria(categorias, consumoTarifaVigenciaDTO)
    }

    private fun calcularConsumoMinimoPorCategoria(
        categorias: List<CategoriaDTO>,
        consumoTarifaVigenciaDTO: ConsumoTarifaVigenciaDTO,
    ): Int {
        var consumoMinimoLigacao = 0
        categorias.forEach { categoria ->
            val consumoMinimoTarifaCategoria = consumoTarifaCategoriaService.obterNumeroConsumoMinimoTarifaCategoria(
                consumoTarifaVigenciaDTO.id,
                categoria.id
            )
            categoria.quantidadeEconomias.let { economias ->
                consumoMinimoLigacao += consumoMinimoTarifaCategoria * economias.toInt()
            }

        }
        return consumoMinimoLigacao
    }
}