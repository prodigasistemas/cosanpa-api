package br.gov.pa.cosanpa.api.dominio.cadastro.geografico

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "unidade_federacao", schema = "cadastro")
data class UnidadeFederacao(
    @Id
    @Column(name = "unfe_id")
    val id: Int? = null,
    @Column(name = "unfe_dsufsigla")
    val sigla: String = ""
)
