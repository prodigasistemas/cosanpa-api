package br.gov.pa.cosanpa.api.dominio.cadastro

import jakarta.persistence.*

@Entity
@Table(name = "cliente", schema = "cadastro")
data class Cliente(
    @Id
    @Column(name = "clie_id")
    val id: Int? = null,
    @Column(name = "clie_nmcliente")
    val nome: String = "",

    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "cliente_imovel",
        schema = "cadastro",
        joinColumns = [JoinColumn(name = "clie_id")],
        inverseJoinColumns = [JoinColumn(name = "imov_id")]
    )
    val imovel: List<Imovel>
)
