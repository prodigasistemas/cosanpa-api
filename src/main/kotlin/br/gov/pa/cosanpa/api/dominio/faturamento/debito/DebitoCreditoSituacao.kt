package br.gov.pa.cosanpa.api.dominio.faturamento.debito

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "debito_credito_situacao", schema = "faturamento")
data class DebitoCreditoSituacao(
    @Id
    @Column(name = "dcst_id")
    val id: Int,
    @Column(name = "dcst_dsdebitocreditosituacao")
    val descricaoDebitoCreditoSituacao: String?
) {
    companion object {
        const val NORMAL = 0
        const val RETIFICADA = 1
        const val INCLUIDA = 2
        const val CANCELADA = 3
        const val CANCELADA_POR_RETIFICACAO = 4
        const val PARCELADA = 5
        const val ARRASTADA = 6
        const val ENTRADA_DE_PARCELAMENTO = 7
        const val DEBITO_PRESCRITO = 8
        const val PRE_FATURADA = 9
        const val CARTAO_CREDITO = 10
        const val ERRO_PROCESSAMENTO = 11
        const val DEBITO_PRESCRITO_CONTAS_INCLUIDAS = 12
        const val PAGA = 13
    }
}
