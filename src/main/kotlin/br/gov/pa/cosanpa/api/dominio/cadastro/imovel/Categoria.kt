package br.gov.pa.cosanpa.api.dominio.cadastro.imovel

import jakarta.persistence.*
import java.math.BigDecimal


@Entity
@Table(name = "categoria", schema = "cadastro")
data class Categoria(
    @Id
    @Column(name = "catg_id")
    val id: Int,
    @Column(name = "catg_dscategoria")
    val descricao: String,
    @Column(name = "catg_nnfatoreconomias")
    val fatorEconomias: Short?,
    @Column(name = "catg_nnconsumoalto")
    val consumoAlto: Int?,
    @Column(name = "catg_nnconsumoestouro")
    val consumoEstouro: Int?,
    @Column(name = "catg_nnconsumomaximoec")
    val numeroConsumoMaximoEc: Int,
    @Column(name = "catg_nnmediabaixoconsumo")
    val mediaBaixoConsumo: Int?,
    @Column(name = "catg_nnvezesmediaaltoconsumo")
    val vezesMediaAltoConsumo: BigDecimal?,
    @Column(name = "catg_nnvezesmediaestouro")
    val vezesMediaEstouro: BigDecimal?,
    @Column(name = "catg_pcmediabaixoconsumo")
    val porcentagemMediaBaixoConsumo: BigDecimal?,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cgtp_id")
    val categoriaTipo: CategoriaTipo

)
