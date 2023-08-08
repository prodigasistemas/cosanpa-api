package br.gov.pa.cosanpa.api.dto.faturamento.consumo

import java.time.LocalDate

data class ConsumoTarifaVigenciaDTO(
    val id: Int? = null,
    val dataVigencia: LocalDate? = null,
)
