package br.gov.pa.cosanpa.api.dominio.cadastro.geografico

import jakarta.persistence.*

@Entity
@Table(name = "municipio", schema = "cadastro")
data class Municipio(
    @Id
    @Column(name = "muni_id")
    val id: Int = 0,
    @Column(name = "muni_nmmunicipio")
    val nome: String = "",

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "unfe_id")
    val unidadeFederacao: UnidadeFederacao
)
