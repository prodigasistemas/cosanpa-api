package br.gov.pa.cosanpa.api.dominio.cadastro

import br.gov.pa.cosanpa.api.dominio.cadastro.endereco.EnderecoReferencia
import br.gov.pa.cosanpa.api.dominio.cadastro.endereco.Logradouro
import br.gov.pa.cosanpa.api.dominio.cadastro.endereco.LogradouroBairro
import br.gov.pa.cosanpa.api.dominio.cadastro.endereco.LogradouroCep
import br.gov.pa.cosanpa.api.dominio.cadastro.localidade.Localidade
import br.gov.pa.cosanpa.api.dominio.cadastro.localidade.Quadra
import br.gov.pa.cosanpa.api.dominio.cadastro.localidade.SetorComercial
import br.gov.pa.cosanpa.api.dominio.faturamento.Conta
import jakarta.persistence.*

@Entity
@Table(name = "imovel", schema = "cadastro")
data class Imovel(
    @Id
    @Column(name = "imov_id")
    val id: Int? = null,
    @Column(name = "imov_nnlote")
    val lote: Int = 0,
    @Column(name = "imov_nnsublote")
    val sublote: Int = 0,
    @Column(name = "imov_nnimovel")
    val numero: String = "",
    @Column(name = "imov_dscomplementoendereco")
    val complementoEndereco: String = "",

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "loca_id")
    val localidade: Localidade,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "qdra_id")
    val quadra: Quadra,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stcm_id")
    val setorComercial: SetorComercial,


    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "cliente_imovel",
        schema = "cadastro",
        joinColumns = [JoinColumn(name = "imov_id")],
        inverseJoinColumns = [JoinColumn(name = "clie_id")]
    )
    val cliente: List<Cliente>,

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "imov_id")
    val conta: List<Conta>,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lgcp_id")
    val logradouroCep: LogradouroCep,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lgbr_id")
    val logradouroBairro: LogradouroBairro,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "edrf_id")
    val enderecoReferencia: EnderecoReferencia,

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "logr_idinicioperimetro", referencedColumnName = "logr_id", insertable = false, updatable = false)
    val perimetroInicial: Logradouro,

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "logr_idfimperimetro", referencedColumnName = "logr_id", insertable = false, updatable = false)
    val perimetroFinal: Logradouro

)
