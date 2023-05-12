package br.gov.pa.cosanpa.api.dto.atendimentopublico.ligacaoagua

data class LigacaoAguaSituacaoDTO(
    val id: Int? = null,
    val descricao: String? = null,
    val indicadorFaturamentoSituacao: Short? = null,
    val indicadorAbastecimento :Short? = null,
    val indicadorConsumoReal: Short? = null,
    val numeroDiasCorte: Int? = null,
)
