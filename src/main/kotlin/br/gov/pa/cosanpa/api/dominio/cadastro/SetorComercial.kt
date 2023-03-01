package br.gov.pa.cosanpa.api.dominio.cadastro

import jakarta.persistence.*

@Entity
@Table(name = "setor_comercial", schema = "cadastro")
data class SetorComercial(
    @Id
    @Column(name = "stcm_id")
    val id: Int? = null,
    @Column(name = "stcm_cdsetorcomercial")
    val codigo: Int = 0,

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "loca_id")
    val localidade: Localidade
)
