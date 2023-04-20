package br.gov.pa.cosanpa.api.dominio.cobranca

import br.gov.pa.cosanpa.api.dominio.faturamento.conta.Conta
import jakarta.persistence.*
import java.math.BigDecimal
import java.time.LocalDate

@Entity
@Table(name = "cobranca_documento_item", schema = "cobranca")
data class CobrancaDocumentoItem(
    @Id
    @Column(name = "cdit_id")
    val id: Int,
    @Column(name = "cdit_vlitemcobrado")
    val valorItemCobrado: BigDecimal?,
    @Column(name = "cdit_dtsituacaodebito")
    val dataSituacaoDebito: LocalDate,
    @Column(name = "cdit_vlacrescimos")
    val valorAcrescimos: BigDecimal,
    @Column(name = "cdit_nnparcelasantecipadas")
    val numeroParcelasAntecipadas: Int,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cbdo_id")
    val cobrancaDocumento: CobrancaDocumento,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dotp_id")
    val documentoTipo: DocumentoTipo,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cnta_id")
    val conta: Conta

)
