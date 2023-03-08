package br.gov.pa.cosanpa.api.dominio.cadastro.imovel

import jakarta.persistence.*


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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cgtp_id")
    val categoriaTipo: CategoriaTipo

)
