package br.gov.pa.cosanpa.api.dominio.cadastro.cliente

import br.gov.pa.cosanpa.api.dominio.faturamento.conta.Conta
import jakarta.persistence.*
import java.util.*

@Entity
@Table(name = "cliente_conta", schema = "cadastro")
data class ClienteConta(
    @Id
    @Column(name = "clct_id")
    val id: Int,
    @Column(name = "clct_icnomeconta")
    val indicadorNomeConta: Int,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "clie_id")
    val cliente: Cliente,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cnta_id")
    val conta: Conta,

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "crtp_id")
    val clienteRelacaoTipo: ClienteRelacaoTipo
)