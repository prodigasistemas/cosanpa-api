package br.gov.pa.cosanpa.api.dominio.operacional

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table

@Entity
@Table(name = "sistema_abastecimento", schema = "operacional")
data class SistemaAbastecimento(
    @Id
    @Column(name = "sabs_id")
    val id: Int,
    @Column(name = "sabs_dssistemaabastecimento")
    val descricao: String?,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ftcp_id")
    val fonteCaptacao: FonteCaptacao
)
