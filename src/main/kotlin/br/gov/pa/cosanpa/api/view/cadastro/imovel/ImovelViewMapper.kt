package br.gov.pa.cosanpa.api.view.cadastro.imovel

import br.gov.pa.cosanpa.api.dto.cadastro.imovel.ImovelDTO
import br.gov.pa.cosanpa.api.view.DtoViewMapper
import org.springframework.stereotype.Component

@Component
class ImovelViewMapper : DtoViewMapper {
    fun mapImovel(entity: ImovelDTO): ImovelView {
        return ImovelView(
            id = entity.id ?: 0,
            lote = entity.lote ?: 0,
            sublote = entity.sublote ?: 0,
            numero = entity.numero?.trim() ?: "",
            nome = entity.nome ?: "",
            complementoEndereco = entity.complementoEndereco ?: "",
            numeroMorador = entity.numeroMorador ?: 0,
            indicadorImovelCondominio = entity.indicadorImovelCondominio ?: 0,
            indicadorExclusao = entity.indicadorExclusao ?: 0,
            numeroSequencialRota = entity.numeroSequencialRota ?: 0,
            codigoDebitoAutomatico = entity.codigoDebitoAutomatico ?: 0,
            indicadorImovelAreaComum = entity.indicadorImovelAreaComum ?: 0,
            indicadorEnvioContaFisica = entity.indicadorEnvioContaFisica ?: 0)
    }
}