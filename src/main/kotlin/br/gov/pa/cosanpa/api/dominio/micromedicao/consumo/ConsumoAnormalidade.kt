package br.gov.pa.cosanpa.api.dominio.micromedicao.consumo

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "consumo_anormalidade", schema = "micromedicao")
data class ConsumoAnormalidade(
    @Id
    @Column(name = "csan_id")
    val id: Int,
    @Column(name = "csan_dsconsumoanormalidade")
    val descricao: String?,
    @Column(name = "csan_iccalcularmedia")
    val indicadorCalcularMedia: Short?
)
