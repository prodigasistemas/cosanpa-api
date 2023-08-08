package br.gov.pa.cosanpa.api.view.faturamento.consumo

import java.math.BigDecimal

data class ConsumoTarifaCategoriaView(
    val id: Int,
    val numeroConsumoMinimo: Int,
    val valorTarifaMinima: BigDecimal,
)
