package br.gov.pa.cosanpa.api.view.leiturista

import br.gov.pa.cosanpa.api.dominio.micromedicao.leiturista.Leiturista
import br.gov.pa.cosanpa.api.util.Mapper
import org.springframework.stereotype.Component

@Component
class LeituristaViewMapper : Mapper<Leiturista, LeituristaView> {
    override fun map(entity: Leiturista): LeituristaView {
        return LeituristaView(
            id = entity.id,
            nomeLeiturista = entity.usuario?.nome,
            nomeEmpresa = entity.empresa.descricao
        )
    }
}