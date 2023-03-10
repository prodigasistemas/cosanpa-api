package br.gov.pa.cosanpa.api.service.faturamento.consumo

import br.gov.pa.cosanpa.api.dto.faturamento.consumo.ConsumoTarifaVigenciaDTO
import br.gov.pa.cosanpa.api.repository.faturamento.consumo.ConsumoTarifaVigenciaRepository
import org.springframework.stereotype.Service
import java.util.*
import kotlin.collections.List

@Service
class ConsumoTarifaVigenciaService(
    private val repository: ConsumoTarifaVigenciaRepository
) {

    fun obterTarifaVigenciaCorrente(idConsumoTarifa: Int): ConsumoTarifaVigenciaDTO {
        return repository.obterConsumoTarifaVigencia(idConsumoTarifa, Date())
    }
}