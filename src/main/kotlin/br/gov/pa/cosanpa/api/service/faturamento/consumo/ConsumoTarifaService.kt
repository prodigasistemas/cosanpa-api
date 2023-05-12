package br.gov.pa.cosanpa.api.service.faturamento.consumo

import br.gov.pa.cosanpa.api.repository.faturamento.consumo.ConsumoTarifaRepository
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class ConsumoTarifaService(
    private val repository: ConsumoTarifaRepository
) {

    fun obterNumeroConsumoMinimoTarifaCategoria(idConsumoTarifaVigencia: Int, idCategoria: Int): Int {
        return repository.obterConsumoMinimoTarifaCategoria(idConsumoTarifaVigencia, idCategoria)
    }

    fun obterTarifaVigenciaCorrente(idConsumoTarifa: Int) = repository.obterDataVigente(idConsumoTarifa, LocalDate.now())


}
