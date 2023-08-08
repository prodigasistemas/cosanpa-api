package br.gov.pa.cosanpa.api.view.faturamento.situacao

data class HistoricoView(
    val id: Int,
    val anoMesFaturamentoRetirada: Int,
    val anoMesFaturamentoSituacaoInicio: Int,
    val anoMesFaturamentoSituacaoFim: Int,
    val numeroConsumoAguaMedido: Int,
    val numeroConsumoNaoAguaMedido: Int,
    val numeroVolumeEsgotoMedido: Int,
    val numeroVolumeEsgotoNaoMedido: Int,
)