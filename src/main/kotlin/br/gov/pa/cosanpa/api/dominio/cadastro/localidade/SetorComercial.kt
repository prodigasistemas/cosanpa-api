package br.gov.pa.cosanpa.api.dominio.cadastro.localidade

import jakarta.persistence.*

@Entity
@Table(name = "setor_comercial", schema = "cadastro")
data class SetorComercial(
    @Id
    @Column(name = "stcm_id")
    val id: Int,
    @Column(name = "stcm_cdsetorcomercial")
    val codigo: Int = 0,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "loca_id")
    val localidade: Localidade
)