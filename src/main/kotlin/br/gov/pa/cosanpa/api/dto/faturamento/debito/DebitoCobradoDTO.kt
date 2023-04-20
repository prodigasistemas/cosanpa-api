package br.gov.pa.cosanpa.api.dto.faturamento.debito

data class DebitoCobradoDTO(
    val id: Int? = null,
    val numeroPrestacao: Short? = null,
    val numeroPrestacaoDebito: Short? = null,
    val numeroParcelaBonus: Short? = null,
    val totalParcela: Number? = null,
    val valorServico: Number? = null,
    val idConta: Int? = null,
    val idDebitoTipo: Int? = null,
    val descricaoDebitoTipo: String? = null,
    val codigoConstanteDebitoTipo: Int? = null,
    val idFinanciamentoTipo: Int? = null
)
