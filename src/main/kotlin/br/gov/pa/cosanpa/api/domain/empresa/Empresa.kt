package br.gov.pa.cosanpa.api.domain.empresa

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "empresa", schema = "cadastro")
data class Empresa(
    @Id
    @Column(name = "empr_id")
    val id: Int? = null,
    @Column(name = "empr_nmempresa")
    val nome: String = ""
)
