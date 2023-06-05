package br.gov.pa.cosanpa.api.view.faturamento.debitocredito

import java.math.BigDecimal

data class DebitoCobradoView (
    val id: Int,
    val referencia: Int,
    val numeroPrestacao: BigDecimal,
    val numeroPrestacaoDebito: Short,
    val numeroParcelaBonus: Short,
    val valorServico: BigDecimal
)
