package br.gov.pa.cosanpa.api.view.atendimentopublico

import br.gov.pa.cosanpa.api.dto.atendimentopublico.ligacaoagua.LigacaoAguaSituacaoDTO
import br.gov.pa.cosanpa.api.view.DtoViewMapper
import br.gov.pa.cosanpa.api.view.atendimentopublico.ligacaoagua.LigacaoAguaSituacaoView
import org.springframework.stereotype.Component

@Component
class LigacaoSituacaoViewMapper : DtoViewMapper {
    fun mapLigacaoAguaSituacao(entity: LigacaoAguaSituacaoDTO): LigacaoAguaSituacaoView {
        return LigacaoAguaSituacaoView(
            id = entity.id ?: 0,
            descricao = entity.descricao ?: "",
            indicadorAbastecimento = entity.indicadorAbastecimento ?: 0
        )
    }
}