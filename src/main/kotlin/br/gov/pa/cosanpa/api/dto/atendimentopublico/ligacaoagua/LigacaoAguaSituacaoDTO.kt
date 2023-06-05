package br.gov.pa.cosanpa.api.dto.atendimentopublico.ligacaoagua

import br.gov.pa.cosanpa.api.dto.IDto

data class LigacaoAguaSituacaoDTO(
    override val id: Int? = null,
    override val descricao: String? = null,
    val indicadorFaturamentoSituacao: Short? = null,
    val indicadorAbastecimento :Short? = null,
    val indicadorConsumoReal: Short? = null,
    val numeroDiasCorte: Int? = null,
) : IDto
