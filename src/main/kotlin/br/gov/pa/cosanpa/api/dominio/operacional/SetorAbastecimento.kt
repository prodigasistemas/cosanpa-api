package br.gov.pa.cosanpa.api.dominio.operacional

import jakarta.persistence.*

@Entity
@Table(name = "setor_abastecimento", schema = "operacional")
data class SetorAbastecimento(
    @Id
    @Column(name = "stab_id")
    val id: Int,
    @Column(name = "stab_dssetorabastecimento")
    val descricao: String,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sabs_id")
    val sistemaAbastecimento: SistemaAbastecimento
)
