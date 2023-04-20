package br.gov.pa.cosanpa.api.dominio.faturamento.conta

import br.gov.pa.cosanpa.api.dominio.faturamento.ImpostoTipo
import jakarta.persistence.*
import java.math.BigDecimal

@Entity
@Table(name = "conta_impostos_deduzidos", schema = "faturamento")
data class ContaImpostosDeduzidos(
    @Id
    @Column(name = "cnid_id")
    val id: Int,
    @Column(name = "cnid_vlbasecalculo")
    val valorBaseCalculo: BigDecimal,
    @Column(name = "cnid_vlimposto")
    val valorImposto: BigDecimal,
    @Column(name = "cnid_pcaliquota")
    val percentualAliquota: BigDecimal,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cnta_id")
    val conta: Conta,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "imtp_id")
    val impostoTipo: ImpostoTipo
)
