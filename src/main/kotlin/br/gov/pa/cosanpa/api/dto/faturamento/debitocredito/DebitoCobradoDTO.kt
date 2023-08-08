package br.gov.pa.cosanpa.api.dto.faturamento.debitocredito

data class DebitoCobradoDTO(
    val id: Int? = null,
    val referencia: Int? = null,
    val numeroPrestacao: Number? = null,
    val numeroPrestacaoDebito: Short? = null,
    val numeroParcelaBonus: Short? = null,
    val valorServico: Number? = null,
    val idConta: Int? = null,
    val idDebitoTipo: Int? = null,
    val idFinanciamentoTipo: Int? = null
)
