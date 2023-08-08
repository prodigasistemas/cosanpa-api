package br.gov.pa.cosanpa.api.dominio.faturamento.conta

import br.gov.pa.cosanpa.api.dominio.cadastro.imovel.Imovel
import jakarta.persistence.*

@Entity
@Table(name = "comunicado_emitir_conta", schema = "faturamento")
data class ComunicadoEmitirConta(
    @Id
    @Column(name = "cmec_id")
    val id: Int,
    @Column(name = "cmec_amreferencia")
    val referencia: Int,
    @Column(name = "cmec_tipo")
    val tipoComunicado: Int?,
    @Column(name = "cmec_icemitido")
    val indicadorEmitido: Short?,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "imov_id")
    val imovel: Imovel

) {
    companion object {
        const val ALTO_CONSUMO = 0
        const val ALTERACAO_CADASTRAL = 1
    }
}