package br.gov.pa.cosanpa.api.dominio.cadastro.cliente

import jakarta.persistence.*

@Entity
@Table(name = "cliente", schema = "cadastro")
data class Cliente(
    @Id
    @Column(name = "clie_id")
    val id: Int,
    @Column(name = "clie_nmcliente")
    val nome: String?,
    @Column(name = "clie_nncpf")
    val cpf: String?,
    @Column(name = "clie_nncnpj")
    val cnpj: String?,

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "clie_id")
    val imovel: List<ClienteImovel>?
)
