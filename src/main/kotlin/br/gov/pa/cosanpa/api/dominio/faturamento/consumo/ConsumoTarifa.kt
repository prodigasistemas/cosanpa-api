package br.gov.pa.cosanpa.api.dominio.faturamento.consumo

import jakarta.persistence.*

@Entity
@Table(name = "consumo_tarifa", schema = "faturamento")
data class ConsumoTarifa(
    @Id
    @Column(name = "cstf_id")
    val id: Int,
    @Column(name = "cstf_dsconsumotarifa")
    val descricao: String?,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ttpc_id")
    val tarifaTipoCalculo: TarifaTipoCalculo
) {
    companion object {
        const val CONSUMO_NORMAL = 1
        const val CONSUMO_SOCIAL = 2
    }
}
