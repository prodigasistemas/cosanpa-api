package br.gov.pa.cosanpa.api.dto.cadastro.endereco

data class EnderecoDTO(
    val logradouroTipoDescricao: String? = null,
    val logradouroTituloDescricao: String? = null,
    val logradouroNome: String? = null,
    val enderecoReferenciaDescricao: String? = null,
    val numeroImovel: String? = null,
    val complementoEndereco: String? = null,
    val bairroNome: String? = null,
    val municipioNome: String? = null,
    val unidadeFederacaoSigla: String? = null,
    val cepCodigo: Int? = null,
    val perimetroInicialLogradouroTipoDescricaoAbreviada: String? = null,
    val perimetroInicialLogradouroTituloDescricaoAbreviada: String? = null,
    val perimetroInicialNome: String? = null,
    val perimetroFinalLogradouroTipoDescricaoAbreviada: String? = null,
    val perimetroFinalLogradouroTituloDescricaoAbreviada: String? = null,
    val perimetroFinalNome: String? = null
)