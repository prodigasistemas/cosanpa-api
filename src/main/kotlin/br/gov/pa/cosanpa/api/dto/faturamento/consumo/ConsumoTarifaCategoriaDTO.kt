package br.gov.pa.cosanpa.api.dto.faturamento.consumo

import java.math.BigDecimal
import java.time.LocalDate

data class ConsumoTarifaCategoriaDTO(
    val id: Int? = null,
    val numeroConsumoMinimo: Int? = null,
    val valorTarifaMinima: BigDecimal? = null,
    val idConsumoTarifa: Int? = null,
    val dataVigenciaConsumoTarifaVigencia: LocalDate? = null,
    val idCategoria: Int? = null,
)
