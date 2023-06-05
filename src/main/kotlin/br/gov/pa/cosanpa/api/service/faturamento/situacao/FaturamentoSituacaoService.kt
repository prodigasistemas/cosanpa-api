package br.gov.pa.cosanpa.api.service.faturamento.situacao

import br.gov.pa.cosanpa.api.repository.faturamento.situacao.FaturamentoSituacaoRepository
import br.gov.pa.cosanpa.api.view.faturamento.situacao.TipoView
import br.gov.pa.cosanpa.api.view.faturamento.situacao.FaturamentoSituacaoViewMapper
import br.gov.pa.cosanpa.api.view.faturamento.situacao.HistoricoView
import org.springframework.stereotype.Service

@Service
class FaturamentoSituacaoService(
    private val repository: FaturamentoSituacaoRepository,
    private val viewMapper: FaturamentoSituacaoViewMapper,
) {

    fun obterDadosTipo(idTipo: Int) = repository.obterDadosTipo(idTipo)

    fun obterTipoView(idTipo: Int): TipoView? {
        return obterDadosTipo(idTipo)?.let {
            viewMapper.mapTipo(
                it
            )
        }
    }

    fun obterDadosHistoricoPorImovelSemRetirada(idImovel: Int, referencia: Int) = repository.obterHistoricoPorImovelSemRetirada(idImovel, referencia)

    fun obterDadosHistoricoPorImovelSemRetiradaView(idImovel: Int, referencia: Int): HistoricoView? {
        return obterDadosHistoricoPorImovelSemRetirada(idImovel, referencia)?.let { viewMapper.mapHistorico(it) }
    }
}