package br.gov.pa.cosanpa.api.dominio.cadastro.endereco

import br.gov.pa.cosanpa.api.dominio.cadastro.geografico.Bairro
import jakarta.persistence.*

@Entity
@Table(name = "logradouro_bairro", schema = "cadastro")
data class LogradouroBairro(
    @Id
    @Column(name = "lgbr_id")
    val id: Int,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "logr_id")
    val logradouro: Logradouro,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bair_id")
    val bairro: Bairro
)