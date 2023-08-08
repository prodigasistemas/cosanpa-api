package br.gov.pa.cosanpa.api.dominio.faturamento.qualidadeagua

import br.gov.pa.cosanpa.api.dominio.cadastro.localidade.Localidade
import br.gov.pa.cosanpa.api.dominio.cadastro.localidade.SetorComercial
import br.gov.pa.cosanpa.api.dominio.operacional.FonteCaptacao
import br.gov.pa.cosanpa.api.dominio.operacional.SistemaAbastecimento
import jakarta.persistence.*
import java.math.BigDecimal

@Entity
@Table(name = "qualidade_agua", schema = "faturamento")
data class QualidadeAgua(
    @Id
    @Column(name = "qlag_id")
    val id: Int,
    @Column(name = "qlag_amreferencia")
    val anoMesReferencia: Int,
    @Column(name = "qlag_nnindiceturbidez")
    val numeroIndiceTurbidez: BigDecimal?,
    @Column(name = "qlag_nnclororesidual")
    val numeroCloroResidual: BigDecimal?,
    @Column(name = "qlag_nnindiceph")
    val numeroIndicePh: BigDecimal?,
    @Column(name = "qlag_nnindicecor")
    val numeroIndiceCor: BigDecimal?,
    @Column(name = "qlag_nnindicefluor")
    val numeroIndiceFluor: BigDecimal?,
    @Column(name = "qlag_nnindiceferro")
    val numeroIndiceFerro: BigDecimal?,
    @Column(name = "qlag_nnindicecoliformestotais")
    val numeroIndiceColiformesTotais: BigDecimal?,
    @Column(name = "qlag_nnindicecoliformesfecais")
    val numeroIndiceColiformesFecais: BigDecimal?,
    @Column(name = "qlag_nnnitrato")
    val numeroNitrato: BigDecimal?,
    @Column(name = "qlag_nnindicealcalinidade")
    val numeroIndiceAlcalinidade: BigDecimal,
    @Column(name = "qlag_qtturbidezexigidas")
    val quantidadeTurbidezExigidas: Int,
    @Column(name = "qlag_qtturbidezanalisadas")
    val quantidadeTurbidezAnalisadas: Int,
    @Column(name = "qlag_qtturbidezconforme")
    val quantidadeTurbidezConforme: Int,
    @Column(name = "qlag_qtcorexigidas")
    val quantidadeCorExigidas: Int,
    @Column(name = "qlag_qtcoranalisadas")
    val quantidadeCorAnalisadas: Int,
    @Column(name = "qlag_qtcorconforme")
    val quantidadeCorConforme: Int,
    @Column(name = "qlag_qtcloroexigidas")
    val quantidadeCloroExigidas: Int,
    @Column(name = "qlag_qtcloroanalisadas")
    val quantidadeCloroAnalisadas: Int,
    @Column(name = "qlag_qtcloroconforme")
    val quantidadeCloroConforme: Int,
    @Column(name = "qlag_qtfluorexigidas")
    val quantidadeFluorExigidas: Int,
    @Column(name = "qlag_qtfluoranalisadas")
    val quantidadeFluorAnalisadas: Int,
    @Column(name = "qlag_qtfluorconforme")
    val quantidadeFluorConforme: Int,
    @Column(name = "qlag_qtcoliftotexigidas")
    val quantidadeColiformesTotaisExigidas: Int,
    @Column(name = "qlag_qtcoliftotanls")
    val quantidadeColiformesTotaisAnalisadas: Int,
    @Column(name = "qlag_qtcoliftotconforme")
    val quantidadeColiformesTotaisConforme: Int,
    @Column(name = "qlag_qtcoliffecexigidas")
    val quantidadeColiformesFecaisExigidas: Int,
    @Column(name = "qlag_qtcoliffecanls")
    val quantidadeColiformesFecaisAnalisadas: Int,
    @Column(name = "qlag_qtcoliffecconforme")
    val quantidadeColiformesFecaisConforme: Int,
    @Column(name = "qlag_nnindicecoliftermo")
    val quantidadeColiformesTermotolerantesExigidas: Int,
    @Column(name = "qlag_qtcoliftermosexigidas")
    val numeroIndiceColiformesTermotolerantes: Int,
    @Column(name = "qlag_qtcoliftermoanls")
    val quantidadeColiformesTermotolerantesAnalisadas: Int,
    @Column(name = "qlag_qtcoliftermoconforme")
    val quantidadeColiformesTermotolerantesConforme: Int,
    @Column(name = "qlag_qtalcalinidadeexigidas")
    val quantidadeAlcalinidadeExigidas: Int,
    @Column(name = "qlag_qtalcalinidadeanalizadas")
    val quantidadeAlcalinidadeAnalisadas: Int,
    @Column(name = "qlag_qtalcalinidadeconforme")
    val quantidadeAlcalinidadeConforme: Int,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "loca_id")
    val localidade: Localidade,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stcm_id")
    val setorComercial: SetorComercial,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ftcp_id")
    val fonteCaptacao: FonteCaptacao,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sabs_id")
    val sistemaAbastecimento: SistemaAbastecimento,
)
