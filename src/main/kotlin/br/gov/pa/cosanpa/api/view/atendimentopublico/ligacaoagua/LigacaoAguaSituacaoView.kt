package br.gov.pa.cosanpa.api.view.atendimentopublico.ligacaoagua

data class LigacaoAguaSituacaoView(
    val id: Int,
    val descricao: String,
    val indicadorAbastecimento: Short
)
