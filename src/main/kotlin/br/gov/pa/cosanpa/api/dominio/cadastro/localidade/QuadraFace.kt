package br.gov.pa.cosanpa.api.dominio.cadastro.localidade

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "quadra_face", schema = "cadastro")
data class QuadraFace(
        @Id
        @Column(name = "qdfa_id")
        val id: Int,
        @Column(name = "qdfa_nnfacequadra")
        val numero: Int
)
