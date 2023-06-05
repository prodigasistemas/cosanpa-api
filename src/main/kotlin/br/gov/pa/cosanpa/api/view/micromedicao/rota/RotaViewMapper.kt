package br.gov.pa.cosanpa.api.view.micromedicao.rota

import br.gov.pa.cosanpa.api.dominio.micromedicao.rota.Rota
import br.gov.pa.cosanpa.api.util.Mapper
import org.springframework.stereotype.Component

@Component
class RotaViewMapper : Mapper<Rota, RotaView> {
    override fun map(entity: Rota): RotaView {
        return RotaView(
            id = entity.id,
            grupo = entity.grupo.id,
            localidade = entity.setorComercial.localidade.id,
            codigoSetorComercial = entity.setorComercial.codigo,
            codigoRota = entity.codigo,
            referencia = entity.grupo.referencia
        )
    }
}
