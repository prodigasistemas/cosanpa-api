package br.gov.pa.cosanpa.api.dominio.cadastro

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "sistema_parametros", schema = "cadastro")
data class SistemaParametros(
    @Id
    @Column(name = "parm_id")
    val id: Int,
    @Column(name = "parm_nnmesescalcmediacons")
    val numeroMesesMediaConsumo: Int,
    @Column(name = "parm_icnaomedidotarifa")
    val indicadorNaoMedidoTarifa: Short
) {
    companion object {
        const val ID = 1
    }
}