package br.gov.pa.cosanpa.api.dominio.cadastro.localidade

import br.gov.pa.cosanpa.api.dominio.cadastro.endereco.DadosEndereco
import br.gov.pa.cosanpa.api.dominio.cadastro.endereco.EnderecoReferencia
import br.gov.pa.cosanpa.api.dominio.cadastro.endereco.LogradouroBairro
import br.gov.pa.cosanpa.api.dominio.cadastro.endereco.LogradouroCep
import br.gov.pa.cosanpa.api.dominio.cadastro.geografico.Municipio
import jakarta.persistence.*

@Entity
@Table(name = "localidade", schema = "cadastro")
data class Localidade(
    @Id
    @Column(name = "loca_id")
    val id: Int,
    @Column(name = "loca_nmlocalidade")
    val descricao: String,
    @Column(name = "loca_nnimovel")
    val numero: String?,
    @Column(name = "loca_dscomplementoendereco")
    val complementoEndereco: String?,
    @Column(name = "loca_nnfone")
    val fone: String?,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lgcp_id")
    val logradouroCep: LogradouroCep,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lgbr_id")
    val logradouroBairro: LogradouroBairro,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "edrf_id")
    val enderecoReferencia: EnderecoReferencia,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "greg_id")
    val gerenciaRegional: GerencialRegional,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "muni_idprincipal")
    val municipio: Municipio
)