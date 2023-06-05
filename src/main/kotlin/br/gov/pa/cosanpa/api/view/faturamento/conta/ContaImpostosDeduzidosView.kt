package br.gov.pa.cosanpa.api.view.faturamento.conta

import java.math.BigDecimal

data class ContaImpostosDeduzidosView(
    val id: Int,
    val valorBaseCalculo: BigDecimal,
    val valorImposto: BigDecimal,
    val percentualAliquota: BigDecimal,
)
