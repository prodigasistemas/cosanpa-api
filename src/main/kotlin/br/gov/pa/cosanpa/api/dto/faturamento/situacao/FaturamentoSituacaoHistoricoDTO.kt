package br.gov.pa.cosanpa.api.dto.faturamento.situacao

data class FaturamentoSituacaoHistoricoDTO(
    val id: Int? = null,
    val anoMesFaturamentoRetirada: Int? = null,
    val anoMesFaturamentoSituacaoInicio: Int? = null,
    val anoMesFaturamentoSituacaoFim: Int? = null,
    val numeroConsumoAguaMedido: Int? = null,
    val numeroConsumoNaoAguaMedido: Int? = null,
    val numeroVolumeEsgotoMedido: Int? = null,
    val numeroVolumeEsgotoNaoMedido: Int? = null,
    val idImovel: Int? = null
)
