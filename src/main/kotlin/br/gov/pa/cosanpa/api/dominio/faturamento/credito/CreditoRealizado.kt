package br.gov.pa.cosanpa.api.dominio.faturamento.credito

import br.gov.pa.cosanpa.api.dominio.faturamento.conta.Conta
import jakarta.persistence.*
import java.math.BigDecimal

@Entity
@Table(name = "credito_realizado", schema = "faturamento")
data class CreditoRealizado(
    @Id
    @Column(name = "crrz_id")
    val id: Int,
    @Column(name = "crrz_amreferenciacredito")
    val anoMesReferenciaCredito: Int,
    @Column(name = "crrz_vlcredito")
    val valorCredito: BigDecimal,
    @Column(name = "crrz_nnprestacao")
    val numeroPrestacao: Short,
    @Column(name = "crrz_nnprestacaocredito")
    val numeroPrestacaoCredito: Short,
    @Column(name = "crrz_nnparcelabonus")
    val numeroParcelaBonus: Short,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cnta_id")
    val conta: Conta,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "crti_id")
    val creditoTipo: CreditoTipo
)