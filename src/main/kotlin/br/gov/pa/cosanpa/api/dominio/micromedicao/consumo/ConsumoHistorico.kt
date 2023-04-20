package br.gov.pa.cosanpa.api.dominio.micromedicao.consumo

import br.gov.pa.cosanpa.api.dominio.cadastro.imovel.Imovel
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table

@Entity
@Table(name = "consumo_historico", schema = "micromedicao")
data class ConsumoHistorico(
    @Id
    @Column(name = "cshi_id")
    val id: Int,
    @Column(name = "cshi_amfaturamento")
    val referencia: Int,
    @Column(name = "cshi_nnconsumocalculomedia")
    val numeroCalculoConsumoMedia: Int?,
    @Column(name = "cshi_nnconsumofaturadomes")
    val numeroConsumoFaturadoMes: Int?,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "imov_id")
    val imovel: Imovel,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cstp_id")
    val consumoTipo: ConsumoTipo,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "csan_id")
    val consumoAnormalidade: ConsumoAnormalidade,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lgti_id")
    val ligacaoTipo: LigacaoTipo
)
