package br.gov.pa.cosanpa.api.dto.faturamento.consumo

import java.math.BigDecimal
import java.time.LocalDate

data class ConsumoTarifaFaixaDTO(
    val id: Int? = null,
    val numeroConsumoFaixaInicio: Int? = null,
    val numeroConsumoFaixaFim: Int? = null,
    val valorConsumoTarifa: BigDecimal? = null,
    val idConsumoTarifa: Int? = null,
    val dataVigenciaConsumoTarifaVigencia: LocalDate? = null,
    val idCategoria: Int? = null
)