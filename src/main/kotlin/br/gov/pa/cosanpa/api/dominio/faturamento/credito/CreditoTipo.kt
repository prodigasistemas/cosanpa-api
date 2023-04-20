package br.gov.pa.cosanpa.api.dominio.faturamento.credito

import jakarta.persistence.*

@Entity
@Table(name = "credito_tipo", schema = "faturamento")
data class CreditoTipo(
    @Id
    @Column(name = "crti_id")
    val id: Int,
    @Column(name = "crti_dscreditotipo")
    val descricao: String,
    @Column(name = "crtp_nncodigoconstante")
    val codigoConstante: Int?
)