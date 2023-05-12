package br.gov.pa.cosanpa.api.repository.atendimentopublico.ligacaoagua

import br.gov.pa.cosanpa.api.dominio.atendimentopublico.ligacaoagua.LigacaoAguaSituacao
import br.gov.pa.cosanpa.api.dto.atendimentopublico.ligacaoagua.LigacaoAguaDTO
import br.gov.pa.cosanpa.api.dto.atendimentopublico.ligacaoagua.LigacaoAguaSituacaoDTO
import br.gov.pa.cosanpa.api.dto.atendimentopublico.ligacaoesgoto.LigacaoEsgotoDTO
import br.gov.pa.cosanpa.api.dto.atendimentopublico.ligacaoesgoto.LigacaoEsgotoSituacaoDTO
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface LigacaoRepository : JpaRepository<LigacaoAguaSituacao, Int> {

    @Query(
        value = " SELECT new br.gov.pa.cosanpa.api.dto.atendimentopublico.ligacaoagua.LigacaoAguaSituacaoDTO( " +
                " last.id as id, " +
                " last.descricao as descricao, " +
                " last.indicadorFaturamentoSituacao as indicadorFaturamentoSituacao, " +
                " last.indicadorAbastecimento as indicadorAbastecimento, " +
                " last.indicadorConsumoReal as indicadorConsumoReal, " +
                " last.numeroDiasCorte as numeroDiasCorte) " +
                " FROM LigacaoAguaSituacao last " +
                " WHERE last.id = :idLigacaoAguaSituacao "
    )
    fun obterDadosLigacaoAguaSituacao(idLigacaoAguaSituacao: Int) : LigacaoAguaSituacaoDTO

    @Query(
        value = " SELECT new br.gov.pa.cosanpa.api.dto.atendimentopublico.ligacaoesgoto.LigacaoEsgotoSituacaoDTO( " +
                " lest.id as id, " +
                " lest.descricao as descricao, " +
                " lest.indicadorFaturamentoSituacao as indicadorFaturamentoSituacao) " +
                " FROM LigacaoEsgotoSituacao lest " +
                " WHERE lest.id = :idLigacaoEsgotoSituacao "
    )
    fun obterDadosLigacaoEsgotoSituacao(idLigacaoEsgotoSituacao: Int): LigacaoEsgotoSituacaoDTO

    @Query(
        value = " SELECT new br.gov.pa.cosanpa.api.dto.atendimentopublico.ligacaoagua.LigacaoAguaDTO( " +
                " lagu.id as id, " +
                " lagu.numeroConsumoMinimoAgua as numeroConsumoMinimoAgua, " +
                " lagu.dataLigacao as dataLigacao, " +
                " lagu.dataCorte as dataCorte, " +
                " lagu.numeroLacre as numeroLacre," +
                " lagu.hidrometroInstalacaoHistorico.id as idHidrometroInstalacaoHistorico) " +
                " FROM LigacaoAgua lagu " +
                " WHERE lagu.id = :idLigacaoAgua "
    )
    fun obterDadosLigacaoAgua(idLigacaoAgua: Int) : LigacaoAguaDTO?

    @Query(
        value = " SELECT new br.gov.pa.cosanpa.api.dto.atendimentopublico.ligacaoesgoto.LigacaoEsgotoDTO( " +
                " lesg.id as id, " +
                " lesg.numeroConsumoMinimoEsgoto as numeroConsumoMinimoEsgoto, " +
                " lesg.percentualAguaConsumidaColetada as percentualAguaConsumidaColetada, " +
                " lesg.dataLigacao as dataLigacao) " +
                " FROM LigacaoEsgoto lesg " +
                " WHERE lesg.id = :idLigacaoEsgoto "
    )
    fun obterDadosLigacaoEsgoto(idLigacaoEsgoto: Int) : LigacaoEsgotoDTO?
}