package br.gov.pa.cosanpa.api.repository.faturamento.situacao

import br.gov.pa.cosanpa.api.dominio.faturamento.situacao.FaturamentoSituacaoHistorico
import br.gov.pa.cosanpa.api.dto.faturamento.situacao.FaturamentoSituacaoHistoricoDTO
import br.gov.pa.cosanpa.api.dto.faturamento.situacao.FaturamentoSituacaoTipoDTO
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface FaturamentoSituacaoRepository: JpaRepository<FaturamentoSituacaoHistorico, Int> {

    @Query(
        value = " SELECT new br.gov.pa.cosanpa.api.dto.faturamento.situacao.FaturamentoSituacaoTipoDTO(" +
                " fst.id as id, " +
                " fst.descricao as descricao, " +
                " fst.leituraAnormalidadeConsumoSemLeitura.id as idLeituraAnormalidadeConsumoSemLeitura, " +
                " fst.leituraAnormalidadeConsumoComLeitura.id as idLeituraAnormalidadeConsumoComLeitura, " +
                " fst.leituraAnormalidadeLeituraSemLeitura.id as idLeituraAnormalidadeLeituraSemLeitura, " +
                " fst.leituraAnormalidadeLeituraComLeitura.id as idLeituraAnormalidadeLeituraComLeitura) " +
                " FROM FaturamentoSituacaoTipo fst " +
                " LEFT JOIN fst.leituraAnormalidadeConsumoSemLeitura " +
                " LEFT JOIN fst.leituraAnormalidadeConsumoComLeitura " +
                " LEFT JOIN fst.leituraAnormalidadeLeituraSemLeitura " +
                " LEFT JOIN fst.leituraAnormalidadeLeituraComLeitura " +
                " WHERE fst.id = :idFaturamentoSituacaoTipo"
    )
    fun obterDadosTipo(idFaturamentoSituacaoTipo: Int): FaturamentoSituacaoTipoDTO?

    @Query(
        value = " SELECT new br.gov.pa.cosanpa.api.dto.faturamento.situacao.FaturamentoSituacaoHistoricoDTO( " +
                " fsh.id as id, " +
                " fsh.anoMesFaturamentoRetirada as anoMesFaturamentoRetirada, " +
                " fsh.anoMesFaturamentoSituacaoInicio as anoMesFaturamentoSituacaoInicio, " +
                " fsh.anoMesFaturamentoSituacaoFim as anoMesFaturamentoSituacaoFim, " +
                " fsh.numeroConsumoAguaMedido as numeroConsumoAguaMedido, " +
                " fsh.numeroConsumoNaoAguaMedido as numeroConsumoNaoAguaMedido, " +
                " fsh.numeroVolumeEsgotoMedido as numeroVolumeEsgotoMedido, " +
                " fsh.numeroVolumeEsgotoNaoMedido as numeroVolumeEsgotoNaoMedido, " +
                " imovel.id as idImovel) " +
                " FROM FaturamentoSituacaoHistorico fsh " +
                " INNER JOIN fsh.imovel imovel" +
                " WHERE imovel.id = :idImovel " +
                " AND fsh.anoMesFaturamentoRetirada is null " +
                " AND fsh.anoMesFaturamentoSituacaoInicio <= :referencia " +
                " AND fsh.anoMesFaturamentoSituacaoFim >= :referencia"
    )
    fun obterHistoricoPorImovelSemRetirada(idImovel: Int, referencia: Int) : FaturamentoSituacaoHistoricoDTO?

}