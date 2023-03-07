package br.gov.pa.cosanpa.api.dominio.micromedicao.consumo

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "ligacao_tipo", schema = "micromedicao")
data class LigacaoTipo(
    @Id
    @Column(name = "lgti_id")
    val id: Int,
    @Column(name = "lgti_dsligacaotipo")
    val descricao: String?
)
