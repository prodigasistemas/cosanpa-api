package br.gov.pa.cosanpa.api.dto.faturamento.debitocredito

import java.math.BigDecimal

data class CreditoRealizadoDTO(
    val id: Int? = null,
    val referencia: Int? = null,
    val valorCredito: BigDecimal? = null,
    val numeroPrestacao: Number? = null,
    val numeroPrestacaoCredito: Short? = null,
    val numeroParcelaBonus: Short? = null,
    val idCreditoTipo: Int? = null
)
