package br.gov.pa.cosanpa.api.dominio.cobranca

import br.gov.pa.cosanpa.api.dominio.cadastro.imovel.Imovel
import br.gov.pa.cosanpa.api.dominio.cadastro.localidade.Localidade
import jakarta.persistence.*
import java.math.BigDecimal
import java.sql.Timestamp

@Entity
@Table(name = "cobranca_documento", schema = "cobranca")
data class CobrancaDocumento(
    @Id
    @Column(name = "cbdo_id")
    val id: Int,
    @Column(name = "cbdo_vldesconto")
    val valorDocumento: BigDecimal?,
    @Column(name = "cbdo_nnsequenciadocumento")
    val numeroSequencialDocumento: Int,
    @Column(name = "cbdo_tmemissao")
    val emissao: Timestamp,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "loca_id")
    val localidade: Localidade,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "imov_id")
    val imovel: Imovel,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dotp_id")
    val documentoTipo: DocumentoTipo,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cdst_id")
    val cobrancaDebitoSituacao: CobrancaDebitoSituacao
)
