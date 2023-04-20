package br.gov.pa.cosanpa.api.dominio.faturamento.debito

import br.gov.pa.cosanpa.api.dominio.faturamento.conta.Conta
import br.gov.pa.cosanpa.api.dominio.financeiro.FinanciamentoTipo
import jakarta.persistence.*
import java.math.BigDecimal

@Entity
@Table(name = "debito_cobrado", schema = "faturamento")
data class DebitoCobrado(
    @Id
    @Column(name = "dbcb_id")
    val id: Int,
    @Column(name = "dbcb_vlprestacao")
    val valorPrestacao: BigDecimal,
    @Column(name = "dbcb_nnprestacao")
    val numeroPrestacao: Short,
    @Column(name = "dbcb_nnprestacaodebito")
    val numeroPrestacaoDebito: Short,
    @Column(name = "dbcb_nnparcelabonus")
    val numeroParcelaBonus: Short,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cnta_id")
    val conta: Conta,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dbtp_id")
    val debitoTipo: DebitoTipo,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fntp_id")
    val financiamentoTipo: FinanciamentoTipo


)
