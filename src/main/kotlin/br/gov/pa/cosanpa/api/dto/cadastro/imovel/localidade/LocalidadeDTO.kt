package br.gov.pa.cosanpa.api.dto.cadastro.imovel.localidade

data class LocalidadeDTO(
    val id: Int? = null,
    val descricao: String? = null,
    val numero: String? = null,
    val complementoEndereco: String? = null,
    val fone: String? = null,
    val idLogradouroCep: Int? = null,
    val idLogradouroBairro: Int? = null,
    val idEnderecoReferencia: Int? = null,
    val idGerenciaRegional: Int? = null,
    val idMunicipio: Int? = null
)
