package br.gov.pa.cosanpa.api.dominio.faturamento.atividade

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "faturamento_atividade", schema = "faturamento")
data class FaturamentoAtividade(
    @Id
    @Column(name = "ftat_id")
    val id: Int,
    @Column(name = "ftat_dsfaturamentoatividade")
    val descricao: String
) {
    companion object {
        const val PRE_FATURAR_GRUPO = 0
        const val GERAR_ARQUIVO_LEITURA = 1
        const val EFETUAR_LEITURA = 2
        const val REGISTRAR_LEITURA_ANORMALIDADE = 3
        const val GERAR_FISCALIZACAO = 4
        const val FATURAR_GRUPO = 5
        const val DISTRIBUIR_CONTAS = 6
        const val TRANSMITIR_ARQUIVO = 7
        const val SIMULAR_FATURAMENTO = 8
        const val CONSISTIR_LEITURAS_E_CALCULAR_CONSUMOS = 9
    }
}
