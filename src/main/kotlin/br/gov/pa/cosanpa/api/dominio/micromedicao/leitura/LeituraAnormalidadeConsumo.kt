package br.gov.pa.cosanpa.api.dominio.micromedicao.leitura

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "leitura_anorm_consumo", schema = "micromedicao")
data class LeituraAnormalidadeConsumo(
    @Id
    @Column(name = "lacs_id")
    val id: Int
)
