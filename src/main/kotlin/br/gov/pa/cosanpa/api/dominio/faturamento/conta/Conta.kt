package br.gov.pa.cosanpa.api.dominio.faturamento.conta

import br.gov.pa.cosanpa.api.dominio.atendimento_publico.ligacaoagua.LigacaoAguaSituacao
import br.gov.pa.cosanpa.api.dominio.atendimento_publico.ligacaoesgoto.LigacaoEsgotoSituacao
import br.gov.pa.cosanpa.api.dominio.cadastro.cliente.ClienteConta
import br.gov.pa.cosanpa.api.dominio.cadastro.imovel.Imovel
import br.gov.pa.cosanpa.api.dominio.cadastro.imovel.ImovelPerfil
import br.gov.pa.cosanpa.api.dominio.cadastro.localidade.Localidade
import br.gov.pa.cosanpa.api.dominio.cadastro.localidade.Quadra
import br.gov.pa.cosanpa.api.dominio.cadastro.localidade.SetorComercial
import br.gov.pa.cosanpa.api.dominio.faturamento.DebitoCreditoSituacao
import br.gov.pa.cosanpa.api.dominio.faturamento.FaturamentoGrupo
import br.gov.pa.cosanpa.api.dominio.faturamento.consumo.ConsumoTarifa
import jakarta.persistence.*
import java.math.BigDecimal
import java.util.*

@Entity
@Table(name = "conta", schema = "faturamento")
data class Conta(
    @Id
    @Column(name = "cnta_id")
    val id: Int,
    @Column(name = "cnta_dtvencimento")
    val dataVencimento: Date,
    @Column(name = "cnta_dtvalidade")
    val dataValidade: Date,
    @Column(name = "cnta_dgverificadorconta")
    val digitoVerificador: Int,
    @Column(name = "cnta_amreferenciaconta")
    val referencia: Int,
    @Column(name = "imov_nnlote")
    val lote: Int,
    @Column(name = "imov_nnsublote")
    val sublote: Int,
    @Column(name = "cnta_cdsetorcomerical")
    val codigoSetorComercial: Int?,
    @Column(name = "cnta_nnquadra")
    val quadra: Int?,
    @Column(name = "cnta_pcesgoto")
    val percentualEsgoto: BigDecimal,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dcst_idatual", referencedColumnName = "dcst_id", insertable = false, updatable = false)
    val debitoCreditoSituacaoAtual: DebitoCreditoSituacao,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dcst_idanterior", referencedColumnName = "dcst_id", insertable = false, updatable = false)
    val debitoCreditoSituacaoAnterior: DebitoCreditoSituacao,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "loca_id")
    val localidade: Localidade,

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "cnta_id")
    val clienteContas: List<ClienteConta>,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "imov_id")
    val imovel: Imovel?,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "iper_id")
    val imovelPerfil: ImovelPerfil,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "last_id")
    val ligacaoAguaSituacao: LigacaoAguaSituacao,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lest_id")
    val ligacaoEsgotoSituacao: LigacaoEsgotoSituacao,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cstf_id")
    val consumoTarifa: ConsumoTarifa,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ftgr_id")
    val faturamentoGrupo: FaturamentoGrupo
)