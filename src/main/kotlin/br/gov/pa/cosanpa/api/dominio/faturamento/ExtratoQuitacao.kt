package br.gov.pa.cosanpa.api.dominio.faturamento

import br.gov.pa.cosanpa.api.dominio.cadastro.imovel.Imovel
import jakarta.persistence.*

@Entity
@Table(name = "extrato_quitacao", schema = "faturamento")
data class ExtratoQuitacao(
    @Id
    @Column(name = "extq_id")
    val id: Int,
    @Column(name = "extq_aareferenciaarrecadacao")
    val anoReferencia: Int,
    @Column(name = "extq_icimpressaoconta")
    val indicadorImpressaoNaConta: Int?,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "imov_id")
    val imovel: Imovel
)
