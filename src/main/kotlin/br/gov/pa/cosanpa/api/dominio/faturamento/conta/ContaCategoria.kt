package br.gov.pa.cosanpa.api.dominio.faturamento.conta

import br.gov.pa.cosanpa.api.dominio.cadastro.imovel.Categoria
import br.gov.pa.cosanpa.api.dominio.cadastro.imovel.Subcategoria
import jakarta.persistence.*
import java.io.Serializable

@Entity
@Table(name = "conta_categoria", schema = "faturamento")
@IdClass(ContaCategoriaPK::class)
data class ContaCategoria(
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cnta_id")
    val conta: Conta,
    
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "catg_id")
    val categoria: Categoria,
    
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "scat_id")
    val subcategoria: Subcategoria,
            
    @Column(name = "ctcg_qteconomia")
    val quantidadeEconomia: Short
)

class ContaCategoriaPK(val conta: Conta? = null, val categoria: Categoria? = null, val subcategoria: Subcategoria? = null) : Serializable {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ContaCategoriaPK

        if (conta != other.conta) return false
        if (categoria != other.categoria) return false
        if (subcategoria != other.subcategoria) return false

        return true
    }

    override fun hashCode(): Int {
        var result = conta?.hashCode() ?: 0
        result = 31 * result + (categoria?.hashCode() ?: 0)
        result = 31 * result + (subcategoria?.hashCode() ?: 0)
        return result
    }
}