package br.gov.pa.cosanpa.api.dominio.cadastro.endereco

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "logradouro_tipo", schema = "cadastro")
data class LogradouroTipo(
    @Id
    @Column(name = "lgtp_id")
    val id: Int,
    @Column(name = "lgtp_dslogradourotipo")
    val descricao: String,
    @Column(name = "lgtp_dsabreviado")
    val descricaoAbreviada: String?
)