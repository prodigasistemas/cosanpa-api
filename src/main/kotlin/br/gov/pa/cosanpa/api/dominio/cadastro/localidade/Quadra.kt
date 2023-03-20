package br.gov.pa.cosanpa.api.dominio.cadastro.localidade

import br.gov.pa.cosanpa.api.dominio.micromedicao.rota.Rota
import jakarta.persistence.*

@Entity
@Table(name = "quadra", schema = "cadastro")
data class Quadra(
    @Id
    @Column(name = "qdra_id")
    val id: Int,
    @Column(name = "qdra_nnquadra")
    val numero: Int,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rota_id")
    val rota: Rota
)