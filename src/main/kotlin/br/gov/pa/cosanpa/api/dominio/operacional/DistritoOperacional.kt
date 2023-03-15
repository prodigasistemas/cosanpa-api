package br.gov.pa.cosanpa.api.dominio.operacional

import jakarta.persistence.*

@Entity
@Table(name = "distrito_operacional", schema = "operacional")
data class DistritoOperacional(
    @Id
    @Column(name = "diop_id")
    val id: Int,
    @Column(name = "diop_dsdistritooperacional")
    val descricao: String,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stab_id")
    val setorAbastecimento: SetorAbastecimento

)
