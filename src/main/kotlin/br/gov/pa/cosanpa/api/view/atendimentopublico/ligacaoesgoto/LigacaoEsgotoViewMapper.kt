package br.gov.pa.cosanpa.api.view.atendimentopublico.ligacaoesgoto

import br.gov.pa.cosanpa.api.dominio.atendimentopublico.ligacaoesgoto.LigacaoEsgoto
import br.gov.pa.cosanpa.api.dto.atendimentopublico.ligacaoagua.LigacaoAguaDTO
import br.gov.pa.cosanpa.api.dto.atendimentopublico.ligacaoesgoto.LigacaoEsgotoDTO
import br.gov.pa.cosanpa.api.extensions.util.conveterLocalDateParaddMMyyyyComBarras
import br.gov.pa.cosanpa.api.util.Mapper
import org.springframework.stereotype.Component
import java.math.BigDecimal

@Component
class LigacaoEsgotoViewMapper : Mapper<LigacaoEsgotoDTO, LigacaoEsgotoView> {
    override fun map(entity: LigacaoEsgotoDTO): LigacaoEsgotoView {
        return LigacaoEsgotoView(
            id = entity.id ?: 0,
            numeroConsumoMinimoEsgoto = entity.numeroConsumoMinimoEsgoto ?: 0,
            dataLigacao = entity.dataLigacao?.conveterLocalDateParaddMMyyyyComBarras() ?: "",
            percentualAguaConsumidaColetada = entity.percentualAguaConsumidaColetada ?: BigDecimal.ZERO
        )
    }
}
