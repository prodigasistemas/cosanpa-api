package br.gov.pa.cosanpa.api.view.rota

import br.gov.pa.cosanpa.api.dominio.micromedicao.Rota
import br.gov.pa.cosanpa.api.util.Mapper
import org.springframework.stereotype.Component

@Component
class RotaViewMapper : Mapper<Rota, RotaView> {
    override fun map(rota: Rota): RotaView {
        return RotaView(
            id = rota.id!!,
            grupo = rota.grupo.id!!,
            localidade = rota.setorComercial.localidade.id!!,
            codigoSetorComercial = rota.setorComercial.codigo,
            codigoRota = rota.codigo,
            referencia = rota.grupo.referencia
        )
    }
}
