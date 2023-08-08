package br.gov.pa.cosanpa.api.dominio.micromedicao

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "rateio_tipo", schema = "micromedicao")
data class RateioTipo(
    @Id
    @Column(name = "rttp_id")
    val id: Int,
    @Column(name = "rttp_dsrateiotipo")
    val descricao: String
) {
    companion object {
        const val SEM_RATEIO = 0
        const val RATEIO_NAO_MEDIDO_AGUA = 4
        const val RATEIO_AREA_COMUM = 5
    }
}