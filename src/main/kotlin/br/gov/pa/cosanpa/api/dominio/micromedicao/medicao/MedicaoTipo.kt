package br.gov.pa.cosanpa.api.dominio.micromedicao.medicao

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "medicao_tipo", schema = "micromedicao")
data class MedicaoTipo(
    @Id
    @Column(name = "medt_id")
    val  id: Int,
    @Column(name = "medt_dsmedicaotipo")
    val descricao: String?
) {
    companion object {
        const val LIGACAO_AGUA = 1
        const val POCO = 2
    }
}
