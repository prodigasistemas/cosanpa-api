package br.gov.pa.cosanpa.api.dto.cadastro.imovel

data class SubcategoriaDTO(
    val id: Int? = null,
    val codigo: Int? = null,
    val descricao: String? = "",
    val quantidadeEconomias: Number? = null,
    val descricaoAbreviada: String? = "",
    val codigoTarifaSocial: String? = "",
    val numeroFatorFiscalizacao: Short? = null,
    val indicadorTarifaConsumo: Short? = null,
    val indicadorSazonalidade: Short? = null,
)
