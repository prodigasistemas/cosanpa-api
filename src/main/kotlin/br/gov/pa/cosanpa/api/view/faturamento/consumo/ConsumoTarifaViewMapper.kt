package br.gov.pa.cosanpa.api.view.faturamento.consumo

import br.gov.pa.cosanpa.api.dto.faturamento.consumo.ConsumoTarifaCategoriaDTO
import br.gov.pa.cosanpa.api.dto.faturamento.consumo.ConsumoTarifaFaixaDTO
import br.gov.pa.cosanpa.api.view.DtoViewMapper
import org.springframework.stereotype.Component
import java.math.BigDecimal
import java.math.RoundingMode

@Component
class ConsumoTarifaViewMapper : DtoViewMapper {

    fun mapConsumoTarifaCategoria(entity: ConsumoTarifaCategoriaDTO): ConsumoTarifaCategoriaView {
        return ConsumoTarifaCategoriaView(
            id = entity.id ?: 0,
            numeroConsumoMinimo = entity.numeroConsumoMinimo ?:0,
            valorTarifaMinima = entity.valorTarifaMinima?.setScale(2, RoundingMode.HALF_UP) ?: BigDecimal.ZERO
        )
    }

    fun mapConsumoTarifaFaixa(entity: ConsumoTarifaFaixaDTO): ConsumoTarifaFaixaView {
        return ConsumoTarifaFaixaView(
            id = entity.id ?: 0,
            numeroConsumoFaixaInicio = entity.numeroConsumoFaixaInicio ?: 0,
            numeroConsumoFaixaFim = entity.numeroConsumoFaixaFim ?: 0,
            valorConsumoTarifa = entity.valorConsumoTarifa?.setScale(2, RoundingMode.HALF_UP) ?: BigDecimal.ZERO
        )
    }
}
