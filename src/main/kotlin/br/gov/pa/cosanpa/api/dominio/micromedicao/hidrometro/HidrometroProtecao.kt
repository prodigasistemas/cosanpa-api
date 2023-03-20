package br.gov.pa.cosanpa.api.dominio.micromedicao.hidrometro

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "hidrometro_protecao", schema = "micromedicao")
data class HidrometroProtecao(
    @Id
    @Column(name = "hipr_id")
    val id: Int,
    @Column(name = "hipr_dshidrometroprotecao")
    val descricao: String
)
