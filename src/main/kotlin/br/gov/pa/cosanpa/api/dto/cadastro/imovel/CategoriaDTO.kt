package br.gov.pa.cosanpa.api.dto.cadastro.imovel

import java.math.BigDecimal

data class CategoriaDTO(
    val id: Int? = null,
    val descricao: String? = null,
    val quantidadeEconomias: Number? = null,
    val consumoAlto: Int? = null,
    val consumoEstouro: Int? = null,
    val numeroConsumoMaximoEc: Int? = null,
    val mediaBaixoConsumo: Int? = null,
    val vezesMediaAltoConsumo: BigDecimal? = null,
    val vezesMediaEstouro: BigDecimal? = null,
    val porcentagemMediaBaixoConsumo: BigDecimal? = null,
    val idSubcategoria: Int? = null,
    val descricaoSubcategoria: String? = null
)
