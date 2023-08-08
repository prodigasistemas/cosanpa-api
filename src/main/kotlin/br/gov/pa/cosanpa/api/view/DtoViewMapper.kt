package br.gov.pa.cosanpa.api.view

import br.gov.pa.cosanpa.api.dto.IDto
import br.gov.pa.cosanpa.api.dto.faturamento.consumo.ConsumoTarifaCategoriaDTO
import br.gov.pa.cosanpa.api.util.Mapper
import br.gov.pa.cosanpa.api.view.faturamento.consumo.ConsumoTarifaCategoriaView
import java.math.BigDecimal

interface DtoViewMapper : Mapper<IDto, IView> {
    override fun map(entity: IDto): IView {
        return View(
            id = entity.id ?: 0,
            descricao = entity.descricao ?: ""
        )

    }
}