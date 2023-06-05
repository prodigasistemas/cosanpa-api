package br.gov.pa.cosanpa.api.view.cobranca

import br.gov.pa.cosanpa.api.dto.cobranca.CobrancaDocumentoItemDTO
import br.gov.pa.cosanpa.api.extensions.util.conveterLocalDateParaddMMyyyyComBarras
import br.gov.pa.cosanpa.api.view.DtoViewMapper
import org.springframework.stereotype.Component
import java.math.BigDecimal
import java.math.RoundingMode

@Component
class CobrancaDocumentoViewMapper : DtoViewMapper {

    fun mapItem(entity: CobrancaDocumentoItemDTO): CobrancaDocumentoItemView {
        return CobrancaDocumentoItemView(
            id = entity.id ?: 0,
            valorItemCobrado = entity.valorItemCobrado?.setScale(2, RoundingMode.HALF_UP) ?: BigDecimal.ZERO,
            dataSituacaoDebito = entity.dataSituacaoDebito?.conveterLocalDateParaddMMyyyyComBarras() ?: "",
            valorAcrescimos = entity.valorAcrescimos?.setScale(2, RoundingMode.HALF_UP) ?: BigDecimal.ZERO,
            numeroParcelasAntecipadas = entity.numeroParcelasAntecipadas ?: 0
        )
    }
}
