package br.gov.pa.cosanpa.api.business.micromedicao.consumo

import br.gov.pa.cosanpa.api.service.cadastro.imovel.ImovelService
import br.gov.pa.cosanpa.api.service.faturamento.consumo.ConsumoTarifaCategoriaService
import br.gov.pa.cosanpa.api.service.faturamento.consumo.ConsumoTarifaVigenciaService
import org.springframework.stereotype.Component


@Component
class ConsumoMinimoLigacaoBO(
    private val imovelService: ImovelService,
    private val consumoTarifaVigenciaService: ConsumoTarifaVigenciaService,
    private val consumoTarifaCategoriaService: ConsumoTarifaCategoriaService
) {
    fun obter(idImovel: Int): Int {
        val imovelDTO = imovelService.obterConsumoTarifaImovel(idImovel)
        val categorias = imovelService.obterDadosCategoriasPorImovel(idImovel)
        val consumoTarifaVigenciaDTO = consumoTarifaVigenciaService.obterTarifaVigenciaCorrente(imovelDTO.consumoTarifa!!)
        var consumoMinimoLigacao = 0

        categorias.forEach { categoria ->
            val consumoMinimoTarifaCategoria = consumoTarifaCategoriaService.obterNumeroConsumoMinimoTarifaCategoria(
                consumoTarifaVigenciaDTO.id,
                categoria.id!!
            )
            consumoMinimoLigacao += consumoMinimoTarifaCategoria * categoria.quantidadeEconomias!!.toInt()
        }

        return consumoMinimoLigacao
    }
}