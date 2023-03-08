package br.gov.pa.cosanpa.api.dominio.cadastro.imovel

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "categoria_tipo", schema = "cadastro")
data class CategoriaTipo(
    @Id
    @Column(name = "cgtp_id")
    val id: Int,
    @Column(name = "cgtp_dscategoriatipo")
    val descricao: String?
)
