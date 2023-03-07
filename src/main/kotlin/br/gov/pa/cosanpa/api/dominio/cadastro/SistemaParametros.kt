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
    val id: Int = 0,
    @Column(name = "parm_nnmesescalcmediacons")
    val numeroMesesMediaConsumo: Int = 0,
    @Column(name = "parm_nnmesesmaximocalculomedia")
    val numeroMesesMaximoCalculoMediaConsumo: Int = 0
) {
    companion object {
        const val ID: Int = 1
    }
}