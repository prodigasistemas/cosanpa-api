package br.gov.pa.cosanpa.api.dominio.micromedicao.consumo

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "consumo_tipo", schema = "micromedicao")
data class ConsumoTipo(
    @Id
    @Column(name = "cstp_id")
    val id: Int,
    @Column(name = "cstp_dsconsumotipo")
    val descricao: String?,
    @Column(name = "cstp_iccalculomedia")
    val indicadorCalcularMedia: Short?
)
