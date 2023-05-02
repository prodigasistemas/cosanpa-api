package br.gov.pa.cosanpa.api.dominio.cadastro.imovel

import br.gov.pa.cosanpa.api.dominio.atendimentopublico.ligacaoagua.LigacaoAgua
import br.gov.pa.cosanpa.api.dominio.atendimentopublico.ligacaoagua.LigacaoAguaSituacao
import br.gov.pa.cosanpa.api.dominio.atendimentopublico.ligacaoesgoto.LigacaoEsgoto
import br.gov.pa.cosanpa.api.dominio.atendimentopublico.ligacaoesgoto.LigacaoEsgotoSituacao
import br.gov.pa.cosanpa.api.dominio.cadastro.cliente.ClienteImovel
import br.gov.pa.cosanpa.api.dominio.cadastro.endereco.EnderecoReferencia
import br.gov.pa.cosanpa.api.dominio.cadastro.endereco.Logradouro
import br.gov.pa.cosanpa.api.dominio.cadastro.endereco.LogradouroBairro
import br.gov.pa.cosanpa.api.dominio.cadastro.endereco.LogradouroCep
import br.gov.pa.cosanpa.api.dominio.cadastro.localidade.Localidade
import br.gov.pa.cosanpa.api.dominio.cadastro.localidade.Quadra
import br.gov.pa.cosanpa.api.dominio.cadastro.localidade.QuadraFace
import br.gov.pa.cosanpa.api.dominio.cadastro.localidade.SetorComercial
import br.gov.pa.cosanpa.api.dominio.faturamento.FaturamentoSituacaoTipo
import br.gov.pa.cosanpa.api.dominio.faturamento.consumo.ConsumoTarifa
import br.gov.pa.cosanpa.api.dominio.faturamento.conta.Conta
import br.gov.pa.cosanpa.api.dominio.micromedicao.hidrometro.HidrometroInstalacaoHistorico
import br.gov.pa.cosanpa.api.dominio.micromedicao.rota.Rota
import jakarta.persistence.*

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
    val numero: String,
    @Column(name = "imov_nmimovel")
    val nome: String?,
    @Column(name = "imov_dscomplementoendereco")
    val complementoEndereco: String?,
    @Column(name = "imov_nnmorador")
    val numeroMorador: Short?,
    @Column(name = "imov_icimovelcondominio")
    val indicadorImovelCondominio: Int,
    @Column(name = "imov_icexclusao")
    val indicadorExclusao: Short?,
    @Column(name = "imov_nnsequencialrota")
    val numeroSequencialRota: Int?,
    @Column(name = "imov_cddebitoautomatico")
    val codigoDebitoAutomatico: Int,
    @Column(name = "imov_icimovelareacomum")
    val indicadorImovelAreaComum: Short?,
    @Column(name = "imov_icenviocontafisica")
    val indicadorEnvioContaFisica: Short,
    @Column(name = "imov_idparametrosconvenio")
    val idParametrosConvenio: Int,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "iper_id")
    val imovelPerfil: ImovelPerfil,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "imov_idimovelcondominio", referencedColumnName = "imov_id")
    val imovelCondominio: Imovel?,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "loca_id")
    val localidade: Localidade,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "qdra_id")
    val quadra: Quadra,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "qdfa_id")
    val quadraFace: QuadraFace,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stcm_id")
    val setorComercial: SetorComercial,

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId("lagu_id")
    val ligacaoAgua: LigacaoAgua,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "last_id")
    val ligacaoAguaSituacao: LigacaoAguaSituacao,

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId("lesg_id")
    val ligacaoEsgoto: LigacaoEsgoto,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lest_id")
    val ligacaoEsgotoSituacao: LigacaoEsgotoSituacao,

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "imov_id")
    val clienteImoveis: List<ClienteImovel>?,

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "imov_id")
    val contas: List<Conta>?,

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
    val perimetroFinal: Logradouro,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cstf_id")
    val consumoTarifa: ConsumoTarifa,

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "imov_id")
    val subcategorias: List<ImovelSubcategoria>?,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "icte_id")
    val imovelContaEnvio: ImovelContaEnvio,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "poco_id")
    val pocoTipo: PocoTipo,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ftst_id")
    val faturamentoSituacaoTipo: FaturamentoSituacaoTipo,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hidi_id")
    val hidrometroInstalacaoHistorico: HidrometroInstalacaoHistorico,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rota_idalternativa", referencedColumnName = "rota_id")
    val rotaAlternativa: Rota

) {
    companion object {
        const val INDICADOR_CONTA_RESPONSAVEL: Short = 1
        const val INDICADOR_CONTA_IMOVEL: Short = 2
        const val INDICADOR_CONTA_NAO_PAGAVEL_PARA_IMOVEL_PAGAVEL_PARA_RESPONSAVEL: Short = 3
        const val IMOVEL_CONDOMINIO: Short = 1
        const val IMOVEL_NAO_CONDOMINIO: Short = 2
        const val IMOVEL_EXCLUIDO: Short = 1
        const val INDICADOR_JARDIM_SIM: Short = 1
        const val INDICADOR_DEBITO_AUTOMATICO: Short = 1
        const val INDICADOR_NAO_DEBITO_AUTOMATICO: Short = 2
        const val INDICADOR_ENVIO_CONTA_FISICA: Short = 1
        const val INDICADOR_NAO_ENVIO_CONTA_FISICA: Short = 2
    }
}
