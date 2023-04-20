package br.gov.pa.cosanpa.api.dominio.cadastro.cliente

import br.gov.pa.cosanpa.api.dominio.cadastro.imovel.Imovel
import jakarta.persistence.*
import java.time.LocalDate

@Entity
@Table(name = "cliente_imovel", schema = "cadastro")
data class ClienteImovel(
    @Id
    @Column(name = "clim_id")
    val id: Int,
    @Column(name = "clim_icnomeconta")
    val indicadorNomeConta: Int,
    @Column(name = "clim_dtrelacaoinicio")
    val dataInicioRelacao: LocalDate,
    @Column(name = "clim_dtrelacaofim")
    val dataFimRelacao: LocalDate?,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "clie_id")
    val cliente: Cliente,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "imov_id")
    val imovel: Imovel,

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "crtp_id")
    val clienteRelacaoTipo: ClienteRelacaoTipo
)
