package br.gov.pa.cosanpa.api.view.micromedicao.consumo

import br.gov.pa.cosanpa.api.dto.micromedicao.consumo.ConsumoHistoricoDTO
import br.gov.pa.cosanpa.api.view.DtoViewMapper
import org.springframework.stereotype.Component

@Component
class ConsumoHistoricoViewMapper : DtoViewMapper {

    fun mapConsumoHistorico(entity: ConsumoHistoricoDTO) : ConsumoHistoricoView {
        return ConsumoHistoricoView(
            id = entity.id ?: 0,
            referencia = entity.referencia ?: 0,
            numeroConsumoFaturadoMes = entity.numeroConsumoFaturadoMes ?: 0
        )
    }

}
