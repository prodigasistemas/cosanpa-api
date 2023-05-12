package br.gov.pa.cosanpa.api.view.cadastro.imovel

import br.gov.pa.cosanpa.api.dto.DTO
import br.gov.pa.cosanpa.api.dto.cadastro.imovel.ImovelDTO
import br.gov.pa.cosanpa.api.util.Mapper
import br.gov.pa.cosanpa.api.view.View
import org.springframework.stereotype.Component

@Component
class ImovelViewMapper : Mapper<ImovelDTO, ImovelView> {
    override fun map(entity: ImovelDTO): ImovelView {
        return ImovelView(
            id = entity.id ?: 0,
            lote = entity.lote ?: 0,
            sublote = entity.sublote ?: 0,
            numero = entity.numero ?: "",
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

    fun mapDtoGenerico(dto: DTO) : View {
        return View(
            id = dto.id ?: 0,
            descricao = dto.descricao ?: ""
        )
    }
}