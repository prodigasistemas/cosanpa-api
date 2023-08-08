package br.gov.pa.cosanpa.api.dominio.cadastro.localidade

import br.gov.pa.cosanpa.api.dominio.operacional.DistritoOperacional
import jakarta.persistence.*

@Entity
@Table(name = "quadra_face", schema = "cadastro")
data class QuadraFace(
        @Id
        @Column(name = "qdfa_id")
        val id: Int,
        @Column(name = "qdfa_nnfacequadra")
        val numero: Int,

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "diop_id")
        val distritoOperacional: DistritoOperacional
)
