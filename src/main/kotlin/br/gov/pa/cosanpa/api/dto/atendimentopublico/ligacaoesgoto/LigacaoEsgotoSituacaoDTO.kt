package br.gov.pa.cosanpa.api.dto.atendimentopublico.ligacaoesgoto

import br.gov.pa.cosanpa.api.dto.IDto

data class LigacaoEsgotoSituacaoDTO(
    override val id: Int? = null,
    override val descricao: String? = null,
    val indicadorFaturamentoSituacao: Short? = null
) : IDto
