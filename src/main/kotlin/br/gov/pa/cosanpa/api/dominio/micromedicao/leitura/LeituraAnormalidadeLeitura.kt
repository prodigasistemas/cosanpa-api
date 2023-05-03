package br.gov.pa.cosanpa.api.dominio.micromedicao.leitura

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "leitura_anorm_leitura", schema = "micromedicao")
data class LeituraAnormalidadeLeitura(
    @Id
    @Column(name = "lalt_id")
    val id: Int,
    @Column(name = "lalt_dsleituraafaturar")
    val descricaoFaturamento: String

) {
    companion object {
        const val ANTERIOR_MAIS_MEDIA = 0
        const val ANTERIOR = 1
        const val ANTERIOR_MAIS_CONSUMO = 2
        const val INFORMADA = 3
        const val NORMAL = 4
    }
}
