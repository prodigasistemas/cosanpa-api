package br.gov.pa.cosanpa.api.dominio.cadastro.geografico

import jakarta.persistence.*

@Entity
@Table(name = "bairro", schema = "cadastro")
data class Bairro(
    @Id
    @Column(name = "bair_id", nullable = false)
    val id: Int? = null,
    @Column(name = "bair_nmbairro")
    val nome: String = "",

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "bair_id", insertable = false, updatable = false)
    val municipio: Municipio
)