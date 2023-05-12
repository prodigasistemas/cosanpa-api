package br.gov.pa.cosanpa.api.view.cadastro.imovel.localidade

import br.gov.pa.cosanpa.api.dto.cadastro.imovel.localidade.LocalidadeDTO
import br.gov.pa.cosanpa.api.util.Mapper
import org.springframework.stereotype.Component

@Component
class LocalidadeViewMapper() : Mapper<LocalidadeDTO, LocalidadeView> {

    override fun map(entity: LocalidadeDTO): LocalidadeView {
        return LocalidadeView(
            id = entity.id ?: 0,
            descricao = entity.descricao ?: ""
        )
    }
}
