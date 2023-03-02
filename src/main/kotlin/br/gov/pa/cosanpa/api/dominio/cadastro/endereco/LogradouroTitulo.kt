package br.gov.pa.cosanpa.api.dominio.cadastro.endereco

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "logradouro_titulo", schema = "cadastro")
data class LogradouroTitulo(
    @Id
    @Column(name = "lgtt_id")
    val id: Int? = null,
    @Column(name = "lgtt_dslogradourotitulo")
    val descricao: String = "",
    @Column(name = "lgtt_dsabreviado")
    val descricaoAbreviada: String = ""
)