package br.gov.pa.cosanpa.api.dominio.micromedicao.hidrometro

import br.gov.pa.cosanpa.api.dominio.micromedicao.medicao.MedicaoTipo
import jakarta.persistence.*
import java.util.Date

@Entity
@Table(name = "hidrometro_inst_hist", schema = "micromedicao")
data class HidrometroInstalacaoHistorico(
    @Id
    @Column(name = "hidi_id")
    val id: Int,
    @Column(name = "hidi_dtinstalacaohidrometro")
    val dataInstalacao: Date,
    @Column(name = "hidi_dtretiradahidrometro")
    val dataRetirada: Date,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hipr_id")
    val hidrometroProtecao: HidrometroProtecao,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hili_id")
    val hidrometroLocalInstalacao: HidrometroLocalInstalacao,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "medt_id")
    val medicaoTipo: MedicaoTipo,
)
