package br.gov.pa.cosanpa.api.dominio.cadastro.endereco

import br.gov.pa.cosanpa.api.dominio.cadastro.geografico.Municipio
import jakarta.persistence.*

@Entity
@Table(name = "logradouro", schema = "cadastro")
data class Logradouro(
    @Id
    @Column(name = "logr_id")
    val id: Int,
    @Column(name = "logr_nmlogradouro")
    val nome: String,

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "logr_id")
    val logradouroCep: List<LogradouroCep>?,

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "logr_id")
    val logradouroBairro: List<LogradouroBairro>?,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lgtp_id")
    val logradouroTipo: LogradouroTipo,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lgtt_id")
    val logradouroTitulo: LogradouroTitulo,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "logr_id", insertable = false, updatable = false)
    val municipio: Municipio
)