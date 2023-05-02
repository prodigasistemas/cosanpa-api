package br.gov.pa.cosanpa.api.dominio.faturamento.consumo

import br.gov.pa.cosanpa.api.dominio.cadastro.imovel.Categoria
import jakarta.persistence.*
import java.math.BigDecimal

@Entity
@Table(name = "consumo_tarifa_categoria", schema = "faturamento")
data class ConsumoTarifaCategoria(
    @Id
    @Column(name = "cstc_id")
    val id: Int,
    @Column(name = "cstc_nnconsumominimo")
    val numeroConsumoMinimo: Int?,
    @Column(name = "cstc_vltarifaminima")
    val valorTarifaMinima: BigDecimal,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cstv_id")
    val consumoTarifaVigencia: ConsumoTarifaVigencia,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "catg_id")
    val categoria: Categoria
)
