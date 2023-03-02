package br.gov.pa.cosanpa.api.dominio.cadastro.localidade

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "gerencia_regional", schema = "cadastro")
data class GerencialRegional(
    @Id
    @Column(name = "greg_id")
    val id: Int? = null,
    @Column(name = "greg_nmregional")
    val nome: String = ""
)