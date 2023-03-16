package br.gov.pa.cosanpa.api.dominio.cadastro.imovel

import jakarta.persistence.*

@Entity
@Table(name = "subcategoria", schema = "cadastro")
data class Subcategoria(
    @Id
    @Column(name = "scat_id")
    val id: Int,
    @Column(name = "scat_cdsubcategoria")
    val codigo: Int,
    @Column(name = "scat_dssubcategoria")
    val descricao: String,
    @Column(name = "scat_dsabreviada")
    val descricaoAbreviada: String?,
    @Column(name = "scat_cdtarifasocial")
    val codigoTarifaSocial: String?,
    @Column(name = "scat_nnfatorfiscalizacao")
    val numeroFatorFiscalizacao: Short,
    @Column(name = "scat_ictarifaconsumo")
    val indicadorTarifaConsumo: Short?,
    @Column(name = "scat_icsazonalidade")
    val indicadorSazonalidade: Short,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "catg_id")
    val categoria: Categoria,

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "scat_id")
    val imovelSubcategoria: List<ImovelSubcategoria>?
) {

}
