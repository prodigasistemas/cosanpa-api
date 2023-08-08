package br.gov.pa.cosanpa.api.dominio.faturamento.debito

import jakarta.persistence.*

@Entity
@Table(name = "debito_tipo", schema = "faturamento")
data class DebitoTipo(
    @Id
    @Column(name = "dbtp_id")
    val id: Int,
    @Column(name = "dbtp_dsdebitotipo")
    val descricao: String,
    @Column(name = "dbtp_nncodigoconstante")
    val codigoConstante: Int?
)
