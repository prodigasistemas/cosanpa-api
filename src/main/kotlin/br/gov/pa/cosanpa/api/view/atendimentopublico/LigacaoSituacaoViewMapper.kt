package br.gov.pa.cosanpa.api.view.atendimentopublico

import br.gov.pa.cosanpa.api.dto.atendimentopublico.ligacaoagua.LigacaoAguaSituacaoDTO
import br.gov.pa.cosanpa.api.dto.atendimentopublico.ligacaoesgoto.LigacaoEsgotoSituacaoDTO
import br.gov.pa.cosanpa.api.util.Mapper
import br.gov.pa.cosanpa.api.view.View
import org.springframework.stereotype.Component

@Component
class LigacaoSituacaoViewMapper : Mapper<LigacaoAguaSituacaoDTO, View> {
    override fun map(entity: LigacaoAguaSituacaoDTO): View {
        return View(
            id = entity.id ?: 0,
            descricao = entity.descricao ?: ""
        )
    }

    fun mapLigacaoEsgotoSituacao(entity: LigacaoEsgotoSituacaoDTO): View {
        return View(
            id = entity.id ?: 0,
            descricao = entity.descricao ?: ""
        )
    }
}