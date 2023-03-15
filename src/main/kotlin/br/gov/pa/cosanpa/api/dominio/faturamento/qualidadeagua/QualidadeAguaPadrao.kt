package br.gov.pa.cosanpa.api.dominio.faturamento.qualidadeagua

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "qualidade_agua_padrao", schema = "faturamento")
data class QualidadeAguaPadrao(
    @Id
    @Column(name = "")
    val id: Int,
    @Column(name = "qlap_dspadraoturbidez")
    val descricaoPadraoTurbidez: String?,
    @Column(name = "qlap_dspadraoph")
    val descricaoPadraoPh: String?,
    @Column(name = "qlap_dspadraocor")
    val descricaoPadraoCor: String?,
    @Column(name = "qlap_dspadraocloro")
    val descricaoPadraoCloro: String?,
    @Column(name = "qlap_dspadraofluor")
    val descricaoPadraoFluor: String?,
    @Column(name = "qlap_dspadraoferro")
    val descricaoPadraoFerro: String?,
    @Column(name = "qlap_dspadraocoliformestotais")
    val descricaoPadraoColiformesTotais: String?,
    @Column(name = "qlap_dspadraocoliformesfecais")
    val descricaoPadraoColiformesFecais: String?,
    @Column(name = "qlap_dspadraonitrato")
    val descricaoNitrato: String?,
    @Column(name = "qlap_dspadraocoliftermo")
    val descricaoPadraoColiformesTermotolerantes: String?,
) {
    companion object {
        const val ID = 1
    }
}
