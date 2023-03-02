package br.gov.pa.cosanpa.api.dominio.cadastro.localidade

import jakarta.persistence.*

@Entity
@Table(name = "localidade", schema = "cadastro")
data class Localidade(
    @Id
    @Column(name = "loca_id")
    val id: Int? = null,
    @Column(name = "loca_nmlocalidade")
    val nome: String = "",

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "greg_id")
    val gerenciaRegional: GerencialRegional

)