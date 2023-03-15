package br.gov.pa.cosanpa.api.dominio.operacional

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table

@Entity
@Table(name = "fonte_captacao", schema = "operacional")
data class FonteCaptacao(
    @Id
    @Column(name = "ftcp_id")
    val id: Int,
    @Column(name = "ftcp_dsfontecaptacao")
    val descricao: String,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tpcp_id")
    val tipoCaptacao: TipoCaptacao
)
