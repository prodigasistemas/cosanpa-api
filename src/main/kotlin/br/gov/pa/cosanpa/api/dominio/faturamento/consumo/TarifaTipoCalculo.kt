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
    @Column(name = "ttpcdstarifatipocalcula")
    val descricao: String?
) {
    companion object {
        const val CALCULO_PROPORCIONAL = 1
        const val CALCULO_SEM_FAIXA_CAER = 2
        const val CALCULO_POR_REFERENCIA = 3
        const val CALCULO_DIRETO_NA_FAIXA = 4
    }
}
