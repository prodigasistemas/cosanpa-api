package br.gov.pa.cosanpa.api.dominio.micromedicao.consumo

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "consumo_tarifa", schema = "faturamento")
data class ConsumoTarifa(
    @Id
    @Column(name = "cstf_id")
    val id: Int,
    @Column(name = "cstf_dsconsumotarifa")
    val descricao: String?
)
