package br.gov.pa.cosanpa.api.dominio.cobranca

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "cobranca_debito_situacao", schema = "cobranca")
data class CobrancaDebitoSituacao(
    @Id
    @Column(name = "cdst_id")
    val id: Int,
    @Column(name = "cdst_dssituacaodebito")
    val descricao: String?
) {
    companion object {
        const val PENDENTE = 1
        const val PAGO = 2
        const val PARCELADO = 3
        const val CANCELADO = 4
        const val SEM_DEBITOS = 5
    }
}
