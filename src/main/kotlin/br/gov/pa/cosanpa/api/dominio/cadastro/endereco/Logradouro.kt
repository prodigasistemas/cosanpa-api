package br.gov.pa.cosanpa.api.dominio.cadastro.endereco

import br.gov.pa.cosanpa.api.dominio.cadastro.geografico.Municipio
import jakarta.persistence.*

@Entity
@Table(name = "logradouro", schema = "cadastro")
data class Logradouro(
    @Id
    @Column(name = "logr_id", insertable = false, updatable = false, nullable = false)
    val id: Int? = null,
    @Column(name = "logr_nmlogradouro")
    val nome: String = "",

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "logr_id", insertable = false, updatable = false)
    val logradouroCep: List<LogradouroCep>,

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "logr_id", insertable = false, updatable = false)
    val logradouroBairro: List<LogradouroBairro>,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "logr_id", insertable = false, updatable = false)
    val logradouroTipo: LogradouroTipo,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "logr_id", insertable = false, updatable = false)
    val logradouroTitulo: LogradouroTitulo,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "logr_id", insertable = false, updatable = false)
    val municipio: Municipio
)