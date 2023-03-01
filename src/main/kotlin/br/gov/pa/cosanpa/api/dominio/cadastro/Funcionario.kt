package br.gov.pa.cosanpa.api.dominio.cadastro

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "funcionario", schema = "cadastro")
data  class Funcionario(
    @Id
    @Column(name = "func_id")
    val id: Int? = null,
    @Column(name = "func_nmfuncionario")
    val nome: String = ""
)
