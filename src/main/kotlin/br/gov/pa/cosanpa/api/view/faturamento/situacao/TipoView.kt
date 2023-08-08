package br.gov.pa.cosanpa.api.view.faturamento.situacao

data class TipoView(
    val id: Int,
    val descricao: String,
    val indicadorValidoAgua: Int,
    val indicadorValidoEsgoto: Int,
    val indicadorParalisacaoFaturamento: Int,
    val indicadorParalisacaoLeitura: Int,
)
