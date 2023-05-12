package br.gov.pa.cosanpa.api.view.arrecadacao.banco

import br.gov.pa.cosanpa.api.dto.DTO
import br.gov.pa.cosanpa.api.dto.arrecadacao.banco.AgenciaDTO
import br.gov.pa.cosanpa.api.util.Mapper
import br.gov.pa.cosanpa.api.view.View
import org.springframework.stereotype.Component

@Component
class BancoViewMapper : Mapper<DTO, View> {

    override fun map(entity: DTO): View {
        return View(
            id = entity.id ?: 0,
            descricao = entity.nome ?: ""
        )
    }

    fun mapAgencia(entity: AgenciaDTO) : View {
        return View (
            id = entity.id ?: 0,
            descricao = entity.codigo.toString()
        )
    }

}
