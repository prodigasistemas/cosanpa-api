package br.gov.pa.cosanpa.api.view.cobranca

import java.math.BigDecimal

data class CobrancaDocumentoItemView(
    val id: Int,
    val valorItemCobrado: BigDecimal,
    val dataSituacaoDebito: String,
    val valorAcrescimos: BigDecimal,
    val numeroParcelasAntecipadas: Int
)
