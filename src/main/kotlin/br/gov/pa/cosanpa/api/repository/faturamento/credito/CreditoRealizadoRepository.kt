package br.gov.pa.cosanpa.api.repository.faturamento.credito

import br.gov.pa.cosanpa.api.dominio.faturamento.credito.CreditoRealizado
import br.gov.pa.cosanpa.api.dto.faturamento.credito.CreditoRealizadoDTO
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface CreditoRealizadoRepository : JpaRepository<CreditoRealizado, Int> {

    @Query(
        value = "SELECT new br.gov.pa.cosanpa.api.dto.faturamento.credito.CreditoRealizadoDTO(" +
                " crrz.anoMesReferenciaCredito as anoMesReferenciaCredito, " +
                " crrz.numeroPrestacaoCredito as numeroPrestacaoCredito,  " +
                " (crrz.numeroPrestacao - COALESCE(crrz.numeroParcelaBonus,0)) as totalParcelas, " +
                " crrz.valorCredito as valorCredito, " +
                " crtp.id as idCreditoTipo, " +
                " crtp.descricao as descricaoCreditoTipo, " +
                " crtp.codigoConstante as codigoConstanteCreditoTipo) " +
                " FROM CreditoRealizado crrz  " +
                " INNER JOIN crrz.conta conta  " +
                " INNER JOIN crrz.creditoTipo crtp  " +
                " WHERE conta.id = :idConta  " +
                " ORDER BY crtp.id, crrz.anoMesReferenciaCredito "
    )
    fun obterCreditosRealizadosPorContaId(idConta: Int) : List<CreditoRealizadoDTO>
}