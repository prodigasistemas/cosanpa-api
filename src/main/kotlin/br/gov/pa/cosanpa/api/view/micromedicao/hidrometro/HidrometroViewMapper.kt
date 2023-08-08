package br.gov.pa.cosanpa.api.view.micromedicao.hidrometro

import br.gov.pa.cosanpa.api.dto.micromedicao.hidrometro.HidrometroDTO
import br.gov.pa.cosanpa.api.dto.micromedicao.hidrometro.HidrometroInstalacaoHistoricoDTO
import br.gov.pa.cosanpa.api.extensions.util.conveterLocalDateParaddMMyyyyComBarras
import br.gov.pa.cosanpa.api.view.DtoViewMapper
import org.springframework.stereotype.Component

@Component
class HidrometroViewMapper : DtoViewMapper {

    fun mapInstalacaoHistorico(entity: HidrometroInstalacaoHistoricoDTO) : HidrometroInstalacaoHistoricoView {
        return HidrometroInstalacaoHistoricoView(
            id = entity.id ?: 0,
            dataInstalacao = entity.dataInstalacao?.conveterLocalDateParaddMMyyyyComBarras() ?: "",
            numeroLeituraInstalacao = entity.numeroLeituraInstalacao ?: 0,
            dataRetirada = entity.dataRetirada?.conveterLocalDateParaddMMyyyyComBarras() ?: ""
        )
    }

    fun mapHidrometro(entity: HidrometroDTO): HidrometroView {
        return HidrometroView(
            id = entity.id ?: 0,
            numero = entity.numero ?: "",
            numeroDigitosLeitura = entity.numeroDigitosLeitura ?: 0
        )
    }
}