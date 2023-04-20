package br.gov.pa.cosanpa.api.dominio.faturamento.consumo

import jakarta.persistence.*
import java.time.LocalDate

@Entity
@Table(name = "consumo_tarifa_vigencia", schema = "faturamento")
data class ConsumoTarifaVigencia(
    @Id
    @Column(name = "cstv_id")
    val id: Int,
    @Column(name = "cstv_dtvigencia")
    val dataVigencia: LocalDate,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cstf_id")
    val consumoTarifa: ConsumoTarifa
)
