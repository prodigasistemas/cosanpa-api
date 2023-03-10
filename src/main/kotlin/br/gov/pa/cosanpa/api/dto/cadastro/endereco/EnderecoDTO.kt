package br.gov.pa.cosanpa.api.dto.cadastro.endereco

data class EnderecoDTO(
    val logradouroTipoDescricao: String?,
    val logradouroTituloDescricao: String?,
    val logradouroNome: String?,
    val enderecoReferenciaDescricao: String?,
    val numeroImovel: String?,
    val complementoEndereco: String?,
    val bairroNome: String?,
    val municipioNome: String?,
    val unidadeFederacaoSigla: String?,
    val cepCodigo: Int?,
    val perimetroInicialLogradouroTipoDescricaoAbreviada: String?,
    val perimetroInicialLogradouroTituloDescricaoAbreviada: String?,
    val perimetroInicialNome: String?,
    val perimetroFinalLogradouroTipoDescricaoAbreviada: String?,
    val perimetroFinalLogradouroTituloDescricaoAbreviada: String?,
    val perimetroFinalNome: String?
)