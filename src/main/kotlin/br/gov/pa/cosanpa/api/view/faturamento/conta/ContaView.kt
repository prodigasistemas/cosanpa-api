package br.gov.pa.cosanpa.api.view.faturamento.conta

import java.math.BigDecimal

data class ContaView(
    val id: Int,
    val referencia: Int,
    val dataVencimento: String,
    val dataValidade: String,
    val digitoVerificador: Int,
    val lote: Int,
    val sublote: Int,
    val percentualEsgoto: BigDecimal,
    val codigoSetorComercial: Int,
    val quadra: Int,
    val idDebitoCreditoSituacaoAtual: Int,
    val idDebitoCreditoSituacaoAnterior: Int
)
