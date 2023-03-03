package br.gov.pa.cosanpa.api.dominio.cadastro.cliente

import jakarta.persistence.*

@Entity
@Table(name = "cliente", schema = "cadastro")
data class Cliente(
    @Id
    @Column(name = "clie_id")
    val id: Int = 0,
    @Column(name = "clie_nmcliente")
    val nome: String = "",

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "clie_id")
    val imovel: List<ClienteImovel>
)
