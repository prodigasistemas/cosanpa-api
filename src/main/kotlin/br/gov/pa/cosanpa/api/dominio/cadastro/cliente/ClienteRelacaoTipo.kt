package br.gov.pa.cosanpa.api.dominio.cadastro.cliente

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "cliente_relacao_tipo", schema = "cadastro")
data class ClienteRelacaoTipo(
    @Id
    @Column(name = "crtp_id")
    val id: Int = 0,
    @Column(name = "crtp_dsclienterelacaotipo")
    val descricao: String = ""
)
