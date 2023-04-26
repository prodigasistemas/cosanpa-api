package br.gov.pa.cosanpa.api.dominio.micromedicao.leitura

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "leitura_situacao", schema = "micromedicao")
data class LeituraSituacao(
    @Id
    @Column(name = "ltst_id")
    val id: Int,
    @Column(name = "ltst_dsleiturasituacao")
    val descricao: String
)
{
    companion object {
        const val REALIZADA = 1
        const val CONFIRMADA = 3
        const val NAO_REALIZADA = 2
        const val LEITURA_ALTERADA = 4
        const val REAL = -1
    }
}
