package br.gov.pa.cosanpa.api.dominio.faturamento

import jakarta.persistence.*

@Entity
@Table(name = "imposto_tipo", schema = "faturamento")
data class ImpostoTipo(
    @Id
    @Column(name = "imtp_id")
    val id: Int,
    @Column(name = "imtp_dsimposto")
    val descricao: String
)
