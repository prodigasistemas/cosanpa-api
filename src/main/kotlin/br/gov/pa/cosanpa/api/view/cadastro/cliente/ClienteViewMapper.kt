package br.gov.pa.cosanpa.api.view.cadastro.cliente

import br.gov.pa.cosanpa.api.dto.cadastro.cliente.ClienteDTO
import br.gov.pa.cosanpa.api.util.Mapper
import org.springframework.stereotype.Component

@Component
class ClienteViewMapper : Mapper<ClienteDTO, ClienteView> {
    override fun map(entity: ClienteDTO): ClienteView {
        var cadastro = ""

        if(!entity.cpf.isNullOrBlank()) {
            cadastro = entity.cpf
        } else if (!entity.cnpj.isNullOrBlank()) {
            cadastro = entity.cnpj
        }

        return ClienteView(
            id = entity.id ?: 0,
            nome = entity.nome ?: "",
            cadastro = cadastro
        )
    }

}
