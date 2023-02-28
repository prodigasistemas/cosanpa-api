package br.gov.pa.cosanpa.api.view.leiturista

import br.gov.pa.cosanpa.api.domain.leiturista.Leiturista
import br.gov.pa.cosanpa.api.util.Mapper
import org.springframework.stereotype.Component

@Component
class LeituristaViewMapper : Mapper<Leiturista, LeituristaView> {
    override fun map(leiturista: Leiturista): LeituristaView {
        return LeituristaView(
            id = leiturista.id,
            nomeLeiturista = leiturista.funcionario.nome,
            nomeEmpresa = leiturista.empresa.nome
        )
    }
}