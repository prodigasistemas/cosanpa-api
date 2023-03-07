package br.gov.pa.cosanpa.api.dominio.cadastro.imovel

import br.gov.pa.cosanpa.api.dominio.atendimento_publico.LigacaoAguaSituacao
import br.gov.pa.cosanpa.api.dominio.atendimento_publico.LigacaoEsgotoSituacao
import br.gov.pa.cosanpa.api.dominio.cadastro.cliente.Cliente
import br.gov.pa.cosanpa.api.dominio.cadastro.cliente.ClienteImovel
import br.gov.pa.cosanpa.api.dominio.cadastro.endereco.EnderecoReferencia
import br.gov.pa.cosanpa.api.dominio.cadastro.endereco.Logradouro
import br.gov.pa.cosanpa.api.dominio.cadastro.endereco.LogradouroBairro
import br.gov.pa.cosanpa.api.dominio.cadastro.endereco.LogradouroCep
import br.gov.pa.cosanpa.api.dominio.cadastro.imovel.ImovelPerfil
import br.gov.pa.cosanpa.api.dominio.cadastro.localidade.Localidade
import br.gov.pa.cosanpa.api.dominio.cadastro.localidade.Quadra
import br.gov.pa.cosanpa.api.dominio.cadastro.localidade.SetorComercial
import br.gov.pa.cosanpa.api.dominio.faturamento.Conta
import br.gov.pa.cosanpa.api.dominio.micromedicao.consumo.ConsumoTarifa
import jakarta.persistence.*
import org.hibernate.annotations.LazyToOne
import org.hibernate.annotations.LazyToOneOption

@Entity
@Table(name = "imovel", schema = "cadastro")
data class Imovel(
    @Id
    @Column(name = "imov_id")
    val id: Int,
    @Column(name = "imov_nnlote")
    val lote: Int,
    @Column(name = "imov_nnsublote")
    val sublote: Int,
    @Column(name = "imov_nnimovel")
    val numero: String?,
    @Column(name = "imov_dscomplementoendereco")
    val complementoEndereco: String?,
    @Column(name = "imov_idimovelcondominio")
    val idImovelCondominio: Int?,
    @Column(name = "imov_icimovelcondominio")
    val indicadorImovelCondominio: Int?,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "iper_id")
    val imovelPerfil: ImovelPerfil?,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "loca_id")
    val localidade: Localidade?,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "qdra_id")
    val quadra: Quadra?,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stcm_id")
    val setorComercial: SetorComercial?,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "last_id")
    val ligacaoAguaSituacao: LigacaoAguaSituacao?,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lest_id")
    val ligacaoEsgotoSituacao: LigacaoEsgotoSituacao?,

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "imov_id")
    val cliente: List<ClienteImovel>?,

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "imov_id")
    val conta: List<Conta>?,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lgcp_id")
    val logradouroCep: LogradouroCep?,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lgbr_id")
    val logradouroBairro: LogradouroBairro?,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "edrf_id")
    val enderecoReferencia: EnderecoReferencia?,

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "logr_idinicioperimetro", referencedColumnName = "logr_id", insertable = false, updatable = false)
    val perimetroInicial: Logradouro?,

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "logr_idfimperimetro", referencedColumnName = "logr_id", insertable = false, updatable = false)
    val perimetroFinal: Logradouro?,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cstf_id")
    val consumoTarifa: ConsumoTarifa?

)
