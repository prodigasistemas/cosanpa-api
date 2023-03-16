package br.gov.pa.cosanpa.api.dominio.faturamento

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "fatur_situacao_hist", schema = "faturamento")
data class FaturamentoSituacaoHistorico(
    @Id
    @Column(name = "ftsh_id")
    val id: Int,
    @Column(name = "ftsh_amfaturamentoretirada")
    val anoMesFaturamentoRetirada: Int?,
    @Column(name = "ftsh_amfatmtsitinicio")
    val anoMesFaturamentoSituacaoInicio: Int,
    @Column(name = "ftsh_amfaturamentosituacaofim")
    val anoMesFaturamentoSituacaoFim: Int,
    @Column(name = "ftsh_nnconsumoaguamedido")
    val numeroConsumoAguaMedido: Int?,
    @Column(name = "ftsh_nnconsumoaguanaomedido")
    val numeroConsumoNaoAguaMedido: Int?,
    @Column(name = "ftsh_nnvolumeesgotomedido")
    val numeroVolumeEsgotoMedido: Int?,
    @Column(name = "ftsh_nnvolumeesgotonaomedido")
    val numeroVolumeEsgotoNaoMedido: Int?,

)
