package br.gov.pa.cosanpa.api.view.arrecadacao.banco

import br.gov.pa.cosanpa.api.dto.Dto
import br.gov.pa.cosanpa.api.dto.arrecadacao.banco.AgenciaDTO
import br.gov.pa.cosanpa.api.util.Mapper
import br.gov.pa.cosanpa.api.view.DtoViewMapper
import br.gov.pa.cosanpa.api.view.View
import org.springframework.stereotype.Component

@Component
class BancoViewMapper : DtoViewMapper {
    fun mapAgencia(entity: AgenciaDTO): LinkedHashMap<String, Any> {
        val retorno = LinkedHashMap<String, Any>()
        retorno["id"] = entity.id ?: 0
        retorno["codigo"]  = entity.codigo ?: ""
        return retorno
    }
}
