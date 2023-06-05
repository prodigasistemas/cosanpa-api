package br.gov.pa.cosanpa.api.view.faturamento.debitocredito

import java.math.BigDecimal

data class CreditoRealizadoView(
    val id: Int,
    val referencia: Int,
    val valorCredito: BigDecimal,
    val numeroPrestacao: Number,
    val numeroPrestacaoCredito: Short,
    val numeroParcelaBonus: Short,
)
