package br.gov.pa.cosanpa.api.dominio.cobranca

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "documento_tipo", schema = "cobranca")
data class DocumentoTipo(
    @Id
    @Column(name = "dotp_id")
    val id: Int,
    @Column(name = "dotp_dsdocumentotipo")
    val descricao: String?
) {
    companion object {
        const val AVISO_CORTE = 12
    }
}
