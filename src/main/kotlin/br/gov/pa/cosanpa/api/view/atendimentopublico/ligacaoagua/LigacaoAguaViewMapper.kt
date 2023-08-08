package br.gov.pa.cosanpa.api.view.atendimentopublico.ligacaoagua

import br.gov.pa.cosanpa.api.dto.atendimentopublico.ligacaoagua.LigacaoAguaDTO
import br.gov.pa.cosanpa.api.extensions.util.conveterLocalDateParaddMMyyyyComBarras
import br.gov.pa.cosanpa.api.util.Mapper
import org.springframework.stereotype.Component

@Component
class LigacaoAguaViewMapper : Mapper<LigacaoAguaDTO, LigacaoAguaView> {
    override fun map(entity: LigacaoAguaDTO): LigacaoAguaView {
        return LigacaoAguaView(
            id = entity.id ?: 0,
            numeroConsumoMinimoAgua = entity.numeroConsumoMinimoAgua ?: 0,
            dataLigacao = entity.dataLigacao?.conveterLocalDateParaddMMyyyyComBarras() ?: "",
            dataCorte = entity.dataCorte?.conveterLocalDateParaddMMyyyyComBarras() ?: "",
            dataReligacao = entity.dataReligacao?.conveterLocalDateParaddMMyyyyComBarras() ?: "",
            numeroLacre = entity.numeroLacre ?: ""
        )
    }
}
