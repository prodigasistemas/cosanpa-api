package br.gov.pa.cosanpa.api.view.faturamento.situacao

import br.gov.pa.cosanpa.api.dto.faturamento.situacao.FaturamentoSituacaoHistoricoDTO
import br.gov.pa.cosanpa.api.dto.faturamento.situacao.FaturamentoSituacaoTipoDTO
import br.gov.pa.cosanpa.api.view.DtoViewMapper
import org.springframework.stereotype.Component

@Component
class FaturamentoSituacaoViewMapper : DtoViewMapper {

    fun mapHistorico(entity: FaturamentoSituacaoHistoricoDTO): HistoricoView {
        return HistoricoView(
            id = entity.id ?: 0,
            anoMesFaturamentoRetirada = entity.anoMesFaturamentoRetirada ?: 0,
            anoMesFaturamentoSituacaoInicio = entity.anoMesFaturamentoSituacaoInicio ?: 0,
            anoMesFaturamentoSituacaoFim = entity.anoMesFaturamentoSituacaoFim ?: 0,
            numeroConsumoAguaMedido = entity.numeroConsumoAguaMedido ?: 0,
            numeroConsumoNaoAguaMedido = entity.numeroConsumoNaoAguaMedido ?: 0,
            numeroVolumeEsgotoMedido = entity.numeroVolumeEsgotoMedido ?: 0,
            numeroVolumeEsgotoNaoMedido = entity.numeroVolumeEsgotoNaoMedido ?: 0,
        )
    }

    fun mapTipo(entity: FaturamentoSituacaoTipoDTO): TipoView {
        return TipoView(
            id = entity.id ?: 0,
            descricao = entity.descricao ?: "",
            indicadorParalisacaoFaturamento = entity.indicadorParalisacaoFaturamento ?: 0,
            indicadorParalisacaoLeitura = entity.indicadorParalisacaoLeitura ?: 0,
            indicadorValidoAgua = entity.indicadorValidoAgua ?: 0,
            indicadorValidoEsgoto = entity.indicadorValidoEsgoto ?: 0
        )
    }

}
