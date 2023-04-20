package br.gov.pa.cosanpa.api.dominio.financeiro

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "financiamento_tipo", schema = "financeiro")
data class FinanciamentoTipo(
    @Id
    @Column(name = "fntp_id")
    val id: Int,
    @Column(name = "fntp_dsfinanciamentotipo")
    val descricao: String
) {
    companion object {
        const val SERVICO_NORMAL = 1
        const val PARCELAMENTO_AGUA = 2
        const val PARCELAMENTO_ESGOTO = 3
        const val PARCELAMENTO_SERVICO = 4
        const val JUROS_PARCELAMENTO = 8
    }
}