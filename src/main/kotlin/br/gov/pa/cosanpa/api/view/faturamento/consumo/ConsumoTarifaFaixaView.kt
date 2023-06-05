package br.gov.pa.cosanpa.api.view.faturamento.consumo

import java.math.BigDecimal

data class ConsumoTarifaFaixaView(
    val id: Int,
    val numeroConsumoFaixaInicio: Int,
    val numeroConsumoFaixaFim: Int,
    val valorConsumoTarifa: BigDecimal
)

