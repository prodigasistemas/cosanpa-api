package br.gov.pa.cosanpa.api.dominio.cadastro.endereco

import jakarta.persistence.*

@Entity
@Table(name = "logradouro_cep", schema = "cadastro")
data class LogradouroCep(
    @Id
    @Column(name = "lgcp_id")
    val id: Int,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "logr_id")
    val logradouro: Logradouro,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cep_id")
    val cep: Cep
)