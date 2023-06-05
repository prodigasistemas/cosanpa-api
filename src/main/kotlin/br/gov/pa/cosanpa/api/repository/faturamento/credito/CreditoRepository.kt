package br.gov.pa.cosanpa.api.repository.faturamento.credito

import br.gov.pa.cosanpa.api.dominio.faturamento.credito.CreditoRealizado
import br.gov.pa.cosanpa.api.dto.faturamento.debitocredito.CreditoRealizadoDTO
import br.gov.pa.cosanpa.api.dto.faturamento.debitocredito.DebitoCreditoTipoDTO
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface CreditoRepository : JpaRepository<CreditoRealizado, Int> {

    @Query(
        value = "SELECT new br.gov.pa.cosanpa.api.dto.faturamento.debitocredito.CreditoRealizadoDTO(" +
                " crrz.id as id, " +
                " crrz.referencia as referencia, " +
                " crrz.valorCredito as valorCredito, " +
                " (crrz.numeroPrestacao - COALESCE(crrz.numeroParcelaBonus,0)) as numeroPrestacao, " +
                " crrz.numeroPrestacaoCredito as numeroPrestacaoCredito, " +
                " crrz.numeroParcelaBonus as numeroParcelaBonus, " +
                " crtp.id as idCreditoTipo)" +
                " FROM CreditoRealizado crrz  " +
                " INNER JOIN crrz.conta conta  " +
                " INNER JOIN crrz.creditoTipo crtp  " +
                " WHERE conta.id = :idConta  " +
                " ORDER BY crtp.id, crrz.referencia "
    )
    fun obterCreditosRealizadosPorContaId(idConta: Int) : List<CreditoRealizadoDTO>

    @Query(
        value = " SELECT new br.gov.pa.cosanpa.api.dto.faturamento.debitocredito.DebitoCreditoTipoDTO( " +
                " ct.id as id, " +
                " ct.descricao as descricao, " +
                " ct.codigoConstante as codigoConstante) " +
                " FROM CreditoTipo ct " +
                " WHERE ct.id = :idCreditoTipo "
    )
    fun obterDadosCreditoTipo(idCreditoTipo: Int): DebitoCreditoTipoDTO
}