package br.gov.pa.cosanpa.api.view.cadastro.imovel.categoria

import java.math.BigDecimal

data class CategoriaView(
    val idCategoria: Int,
    val descricaoCategoria: String,
    val quantidadeEconomias: Int,
    val idSubcategoria: Int,
    val descricaoSubcategoria: String,
    val consumoReferenciaBaixoConsumo: Int,
    val consumoReferenciaAltoConsumo: Int,
    val consumoReferenciaEstouroConsumo: Int,
    val consumoMaximoEstouroConsumo: Int,
    val percentualDeterminacaoBaixoConsumo: BigDecimal,
    val vezesMediaAltoConsumo: BigDecimal,
    val vezesMediaEstouroConsumo: BigDecimal
)