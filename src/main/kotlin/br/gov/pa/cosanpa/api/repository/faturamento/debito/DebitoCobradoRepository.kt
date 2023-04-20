package br.gov.pa.cosanpa.api.repository.faturamento.debito

import br.gov.pa.cosanpa.api.dominio.faturamento.debito.DebitoCobrado
import br.gov.pa.cosanpa.api.dominio.financeiro.FinanciamentoTipo
import br.gov.pa.cosanpa.api.dto.faturamento.debito.DebitoCobradoDTO
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface DebitoCobradoRepository : JpaRepository<DebitoCobrado, Int> {
    
    @Query(
        value = "SELECT new br.gov.pa.cosanpa.api.dto.faturamento.debito.DebitoCobradoDTO( " +
                " dbcb.numeroPrestacaoDebito as numeroPrestacaoDebito, " +
                " (dbcb.numeroPrestacao - COALESCE(dbcb.numeroParcelaBonus,0)) as totalParcela, " +
                " SUM(dbcb.valorPrestacao) as valorServico, " +
                " dbcb.debitoTipo.id as idDebitoTipo, " +
                " dbcb.debitoTipo.descricao as descricaoDebitoTipo, " +
                " dbcb.debitoTipo.codigoConstante as codigoConstanteDebitoTipo, " +
                " fntp.id as idFinanciamentoTipo) " +
                " FROM DebitoCobrado dbcb " +
                " INNER JOIN dbcb.conta conta " +
                " INNER JOIN dbcb.financiamentoTipo fntp " +
                " WHERE conta.id = :idConta AND " +
                " fntp.id IN ( ${FinanciamentoTipo.PARCELAMENTO_AGUA}, " +
                "              ${FinanciamentoTipo.PARCELAMENTO_ESGOTO}, " +
                "              ${FinanciamentoTipo.PARCELAMENTO_SERVICO}) " +
                " GROUP BY 1,2,4,5,6,7 "
    )
    fun obterDebitoCobradoDeParcelamentoIS(idConta: Int) : List<DebitoCobradoDTO>
}