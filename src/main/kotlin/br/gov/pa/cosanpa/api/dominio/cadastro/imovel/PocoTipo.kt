package br.gov.pa.cosanpa.api.dominio.cadastro.imovel

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "poco_tipo", schema = "cadastro")
data class PocoTipo(
    @Id
    @Column(name = "poco_id")
    val id: Int,
    @Column(name = "poco_dspocotipo")
    val descricao: String?
)
