package br.gov.pa.cosanpa.api.view

import br.gov.pa.cosanpa.api.dto.DTO
import br.gov.pa.cosanpa.api.util.Mapper
import org.springframework.stereotype.Component

@Component
class DtoViewMapper : Mapper<DTO, View> {
    override fun map(entity: DTO): View {
        return View(
            id = entity.id ?: 0,
            descricao = entity.descricao ?: ""
        )
    }
}