package br.gov.pa.cosanpa.api.dominio.cadastro.localidade

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "quadra", schema = "cadastro")
data class Quadra(
    @Id
    @Column(name = "qdra_id")
    val id: Int,
    @Column(name = "qdra_nnquadra")
    val numero: Int
)