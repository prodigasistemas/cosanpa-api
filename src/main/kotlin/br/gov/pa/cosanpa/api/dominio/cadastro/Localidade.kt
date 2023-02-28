package br.gov.pa.cosanpa.api.dominio.cadastro

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "localidade", schema = "cadastro")
data class Localidade(
    @Id
    @Column(name = "loca_id")
    val id: Int? = null
)
