package br.gov.pa.cosanpa.api.dominio.arrecadacao

import jakarta.persistence.*

@Entity
@Table(name = "agencia", schema = "arrecadacao")
data class Agencia(
    @Id
    @Column(name = "agen_id")
    val id: Int = 0,
    @Column(name = "agen_cdagencia")
    val codigo: String = "",

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bnco_id")
    val banco: Banco
)
