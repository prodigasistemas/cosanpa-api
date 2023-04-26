package br.gov.pa.cosanpa.api.dominio.micromedicao.medicao

import br.gov.pa.cosanpa.api.dominio.atendimento_publico.ligacaoagua.LigacaoAgua
import br.gov.pa.cosanpa.api.dominio.cadastro.imovel.Imovel
import br.gov.pa.cosanpa.api.dominio.micromedicao.leitura.LeituraSituacao
import jakarta.persistence.*
import java.time.LocalDate

@Entity
@Table(name = "medicao_historico", schema = "micromedicao")
data class MedicaoHistorico(
    @Id
    @Column(name = "mdhi_id")
    val id : Int,
    @Column(name = "mdhi_amleitura")
    val anoMesReferencia: Int,
    @Column(name = "mdhi_dtleitantfatmt")
    val dataLeituraAnteriorFaturamento: LocalDate?,
    @Column(name = "mdhi_nnleitantfatmt")
    val leituraAnterioFaturamento: Int,
    @Column(name = "mdhi_nnleitantinformada")
    val leituraAnteriorInformada: Int?,
    @Column(name = "mdhi_dtleituraatualinformada")
    val dataLeituraAtualInformada: LocalDate?,
    @Column(name = "mdhi_nnleituraatualinformada")
    val leituraAtualInformada: Int?,
    @Column(name = "mdhi_dtleituraatualfaturamento")
    val dataLeituraAtualFaturamento: LocalDate,
    @Column(name = "mdhi_nnleituraatualfaturamento")
    val leituraAtualFaturamento: Int,
    @Column(name = "mdhi_nnconsumomediohidrometro")
    val consumoMedioHidrometro: Int,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "imov_id")
    val imovel: Imovel?,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lagu_id")
    val ligacaoAgua: LigacaoAgua?,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "medt_id")
    val medicaoTipo: MedicaoTipo,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ltst_idleiturasituacaoanterior", referencedColumnName = "ltst_id", insertable = false, updatable = false)
    val leituraSituacaoAnterior: LeituraSituacao,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ltst_idleiturasituacaoatual", referencedColumnName = "ltst_id", insertable = false, updatable = false)
    val leituraSituacaoAtual: LeituraSituacao,
)