package br.gov.pa.cosanpa.api.dto.faturamento.credito

import java.math.BigDecimal

data class CreditoRealizadoDTO(
    val id: Int? = null,
    val anoMesReferenciaCredito: Int? = null,
    val valorCredito: BigDecimal? = null,
    val numeroPrestacao: Short? = null,
    val numeroPrestacaoCredito: Short? = null,
    val numeroParcelaBonus: Short? = null,
    val totalParcelas: Number? = null,
    val idCreditoTipo: Int? = null,
    val descricaoCreditoTipo: String? = null,
    val codigoConstanteCreditoTipo: Int? = null
)
