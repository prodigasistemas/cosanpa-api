package br.gov.pa.cosanpa.api.dominio.micromedicao.hidrometro

import jakarta.persistence.*

@Entity
@Table(name = "hidrometro", schema = "micromedicao")
data class Hidrometro(
    @Id
    @Column(name = "hidr_id")
    val id: Int,
    @Column(name = "hidr_nnhidrometro")
    val numero: String,
    @Column(name = "hidr_nndigitosleitura")
    val numeroDigitosLeitura: Short
)