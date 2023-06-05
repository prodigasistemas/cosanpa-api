package br.gov.pa.cosanpa.api.dominio.faturamento.consumo

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "tarifa_tipo_calculo", schema = "faturamento")
data class TarifaTipoCalculo(
    @Id
    @Column(name = "ttpc_id")
    val id: Int,
    @Column(name = "ttpc_dstarifatipocalculo")
    val descricao: String?,
    @Column(name = "ttpc_icuso")
    val indicadorUso: Short
) {
    companion object {
        const val CALCULO_PROPORCIONAL = 1
    }
}
