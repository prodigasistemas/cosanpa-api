package br.gov.pa.cosanpa.api.dominio.cadastro.endereco

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "endereco_referencia", schema = "cadastro")
data class EnderecoReferencia(
    @Id
    @Column(name = "edrf_id")
    val id: Int = 0,
    @Column(name = "edrf_dsenderecoreferencia")
    val descricao: String = "",
    @Column(name = "edrf_dsabreviado")
    val descricaoAbreviada: String = ""
)