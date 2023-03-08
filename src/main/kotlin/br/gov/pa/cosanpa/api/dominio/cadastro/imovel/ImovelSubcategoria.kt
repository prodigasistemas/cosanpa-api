package br.gov.pa.cosanpa.api.dominio.cadastro.imovel

import jakarta.persistence.*
import java.io.Serializable

@Entity
@Table(name = "imovel_subcategoria", schema = "cadastro")
@IdClass(ImovelSubcategoriaPK::class)
data class ImovelSubcategoria(
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "imov_id")
    val imovel: Imovel,
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "scat_id")
    val subcategoria: Subcategoria,
    @Column(name = "imsb_qteconomia")
    val quantidadeEconomias: Short
)

class ImovelSubcategoriaPK(val imovel: Imovel? = null, val subcategoria: Subcategoria? = null) : Serializable {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ImovelSubcategoriaPK

        if (imovel != other.imovel) return false
        if (subcategoria != other.subcategoria) return false

        return true
    }

    override fun hashCode(): Int {
        var result = imovel?.hashCode() ?: 0
        result = 31 * result + (subcategoria?.hashCode() ?: 0)
        return result
    }
}
