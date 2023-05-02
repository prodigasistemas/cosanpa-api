package br.gov.pa.cosanpa.api.dominio.faturamento.consumo

import jakarta.persistence.*
import java.math.BigDecimal

@Entity
@Table(name = "consumo_tarifa_faixa", schema = "faturamento")
data class ConsumoTarifaFaixa(
    @Id
    @Column(name = "ctfx_id")
    val id: Int,
    @Column(name = "ctfx_nncosumofaixainicio")
    val numeroConsumoFaixaInicio: Int,
    @Column(name = "ctfx_nnconsumofaixafim")
    val numeroConsumoFaixaFim: Int,
    @Column(name = "ctfx_vlconsumotarifa")
    val valorConsumoTarifa: BigDecimal,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cstc_id")
    val consumoTarifaCategoria: ConsumoTarifaCategoria
)

