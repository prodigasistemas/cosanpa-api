package br.gov.pa.cosanpa.api.dominio.arrecadacao

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "banco", schema = "arrecadacao")
data class Banco(
    @Id
    @Column(name = "bnco_id")
    val id: Int,
    @Column(name = "bnco_nmbanco")
    val nome: String?
)
