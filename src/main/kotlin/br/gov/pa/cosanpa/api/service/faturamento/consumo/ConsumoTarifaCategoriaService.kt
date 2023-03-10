package br.gov.pa.cosanpa.api.service.faturamento.consumo

import br.gov.pa.cosanpa.api.repository.faturamento.consumo.ConsumoTarifaCategoriaRepository
import org.springframework.stereotype.Service

@Service
class ConsumoTarifaCategoriaService(
    private val repository: ConsumoTarifaCategoriaRepository
) {

    fun obterNumeroConsumoMinimoTarifaCategoria(idConsumoTarifaVigencia: Int, idCategoria: Int): Int {
        return repository.obterConsumoMinimoTarifaCategoria(idConsumoTarifaVigencia, idCategoria)
    }

}