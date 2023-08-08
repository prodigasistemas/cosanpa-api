package br.gov.pa.cosanpa.api.repository.faturamento.debito

import br.gov.pa.cosanpa.api.dominio.faturamento.debito.DebitoCobrado
import br.gov.pa.cosanpa.api.dominio.financeiro.FinanciamentoTipo
import br.gov.pa.cosanpa.api.dto.Dto
import br.gov.pa.cosanpa.api.dto.faturamento.debitocredito.DebitoCobradoDTO
import br.gov.pa.cosanpa.api.dto.faturamento.debitocredito.DebitoCreditoTipoDTO
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface DebitoRepository : JpaRepository<DebitoCobrado, Int> {

    @Query(
        value = "SELECT new br.gov.pa.cosanpa.api.dto.faturamento.debitocredito.DebitoCobradoDTO( " +
                " dbcb.id as id, " +
                " dbcb.referencia as referencia, " +
                " (dbcb.numeroPrestacao - COALESCE(dbcb.numeroParcelaBonus,0)) as numeroPrestacao," +
                " dbcb.numeroPrestacaoDebito as numeroPrestacaoDebito, " +
                " SUM(dbcb.valorPrestacao) as valorServico, " +
                " dbcb.debitoTipo.id as idDebitoTipo, " +
                " fntp.id as idFinanciamentoTipo) " +
                " FROM DebitoCobrado dbcb " +
                " INNER JOIN dbcb.conta conta " +
                " INNER JOIN dbcb.financiamentoTipo fntp " +
                " WHERE conta.id = :idConta AND " +
                " fntp.id IN (${FinanciamentoTipo.PARCELAMENTO_AGUA}, " +
                "              ${FinanciamentoTipo.PARCELAMENTO_ESGOTO}, " +
                "              ${FinanciamentoTipo.PARCELAMENTO_SERVICO})  " +
                " GROUP BY 1,2,4,6 "
    )
    fun obterColecaoDebitoCobradoDeParcelamento(idConta: Int) : List<DebitoCobradoDTO>

    @Query(
        value = "SELECT new br.gov.pa.cosanpa.api.dto.faturamento.debitocredito.DebitoCobradoDTO( " +
                " dbcb.id as id, " +
                " dbcb.referencia as referencia, " +
                " (dbcb.numeroPrestacao - COALESCE(dbcb.numeroParcelaBonus,0)) as numeroPrestacao," +
                " dbcb.numeroPrestacaoDebito as numeroPrestacaoDebito, " +
                " dbcb.valorPrestacao as valorServico, " +
                " dbcb.debitoTipo.id as idDebitoTipo, " +
                " fntp.id as idFinanciamentoTipo) " +
                " FROM DebitoCobrado dbcb " +
                " INNER JOIN dbcb.conta conta " +
                " INNER JOIN dbcb.financiamentoTipo fntp " +
                " WHERE conta.id = :idConta AND " +
                " fntp.id NOT IN (${FinanciamentoTipo.PARCELAMENTO_AGUA}, " +
                "              ${FinanciamentoTipo.PARCELAMENTO_ESGOTO}, " +
                "              ${FinanciamentoTipo.PARCELAMENTO_SERVICO})  " +
                " GROUP BY 1,2,4,6 "
    )
    fun obterDebitoCobradoNaoParcelamento(idConta: Int) : List<DebitoCobradoDTO>

    @Query(
        value = " SELECT new br.gov.pa.cosanpa.api.dto.faturamento.debitocredito.DebitoCreditoTipoDTO( " +
                " dt.id as id, " +
                " dt.descricao as descricao, " +
                " dt.codigoConstante as codigoConstante) " +
                " FROM DebitoTipo dt " +
                " WHERE dt.id = :idDebitoTipo "
    )
    fun obterDadosDebitoTipo(idDebitoTipo: Int): DebitoCreditoTipoDTO

    @Query(
        value = " SELECT new br.gov.pa.cosanpa.api.dto.Dto( " +
                " ft.id as id," +
                " ft.descricao as descricao) " +
                " FROM FinanciamentoTipo ft " +
                " WHERE ft.id = :idFinanciamentoTipo "
    )
    fun obterDadosFinanciamentoTipo(idFinanciamentoTipo: Int): Dto
}