package br.gov.pa.cosanpa.api.dominio.micromedicao.leitura

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "leitura_anorm_leitura", schema = "micromedicao")
data class LeituraAnormalidadeLeitura(
    @Id
    @Column(name = "lalt_id")
    val id: Int
)
