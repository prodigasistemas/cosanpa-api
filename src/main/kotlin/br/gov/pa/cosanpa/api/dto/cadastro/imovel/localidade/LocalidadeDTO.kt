package br.gov.pa.cosanpa.api.dto.cadastro.imovel.localidade

import br.gov.pa.cosanpa.api.dto.IDto

data class LocalidadeDTO(
    override val id: Int? = null,
    override val descricao: String? = null,
    val numero: String? = null,
    val complementoEndereco: String? = null,
    val fone: String? = null,
    val idLogradouroCep: Int? = null,
    val idLogradouroBairro: Int? = null,
    val idEnderecoReferencia: Int? = null,
    val idGerenciaRegional: Int? = null,
    val idMunicipio: Int? = null
) : IDto
