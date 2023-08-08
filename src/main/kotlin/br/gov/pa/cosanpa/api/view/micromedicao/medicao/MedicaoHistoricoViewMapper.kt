package br.gov.pa.cosanpa.api.view.micromedicao.medicao

import br.gov.pa.cosanpa.api.dto.micromedicao.medicao.MedicaoHistoricoDTO
import br.gov.pa.cosanpa.api.extensions.util.conveterLocalDateParaddMMyyyyComBarras
import br.gov.pa.cosanpa.api.view.DtoViewMapper
import org.springframework.stereotype.Component

@Component
class MedicaoHistoricoViewMapper : DtoViewMapper{

    fun mapMedicaoHistorico(entity: MedicaoHistoricoDTO): MedicaoHistoricoView {
        return MedicaoHistoricoView(
            id = entity.id ?: 0,
            anoMesReferencia = entity.anoMesReferencia ?: 0,
            dataLeituraAnteriorFaturamento = entity.dataLeituraAnteriorFaturamento?.conveterLocalDateParaddMMyyyyComBarras() ?: "",
            leituraAnteriorInformada = entity.leituraAnteriorInformada ?: 0,
            leituraAnterioFaturamento = entity.leituraAnterioFaturamento ?: 0,
            dataLeituraAtualInformada = entity.dataLeituraAtualInformada?.conveterLocalDateParaddMMyyyyComBarras() ?: "",
            leituraAtualInformada = entity.leituraAtualFaturamento ?: 0,
            dataLeituraAtualFaturamento = entity.dataLeituraAtualFaturamento?.conveterLocalDateParaddMMyyyyComBarras() ?: "",
            leituraAtualFaturamento = entity.leituraAtualFaturamento ?: 0,
            consumoMedioHidrometro = entity.consumoMedioHidrometro ?: 0
        )
    }
}