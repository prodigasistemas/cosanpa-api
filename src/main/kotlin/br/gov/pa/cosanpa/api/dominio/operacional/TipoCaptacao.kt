package br.gov.pa.cosanpa.api.dominio.operacional

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "tipo_captacao", schema = "operacional")
data class TipoCaptacao(
    @Id
    @Column(name = "tpcp_id")
    val id: Int,
    @Column(name = "tpcp_dstipocaptacao")
    val descricao: String
)
