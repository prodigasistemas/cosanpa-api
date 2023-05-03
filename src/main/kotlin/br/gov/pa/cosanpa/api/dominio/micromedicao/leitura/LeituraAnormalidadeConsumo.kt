package br.gov.pa.cosanpa.api.dominio.micromedicao.leitura

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "leitura_anorm_consumo", schema = "micromedicao")
data class LeituraAnormalidadeConsumo(
    @Id
    @Column(name = "lacs_id")
    val id: Int,
    @Column(name = "lacs_dsconsumoacobrar")
    val descricaoConsumoACobrar: String
) {
    companion object {
        const val NAO_OCORRE = 0
        const val MINIMO = 1
        const val MEDIA = 2
        const val NORMAL = 3
        const val MAIOR_ENTRE_CONSUMO_MEDIO_HIDROMETRO_CONSUMO_MEDIDO = 5
        const val MENOR_ENTRE_CONSUMO_MEDIO_HIDROMETRO_CONSUMO_MEDIDO = 6
        const val FIXO = 7
        const val NAO_MEDIDO = 8
    }
}
