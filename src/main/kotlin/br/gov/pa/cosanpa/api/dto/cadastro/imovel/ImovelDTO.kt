package br.gov.pa.cosanpa.api.dto.cadastro.imovel

data class ImovelDTO(
    val id: Int? = null,
    val lote: Int? = null,
    val sublote: Int? = null,
    val numero: String? = null,
    val complementoEndereco: String? = null,
    val idImovelCondominio: Int? = null,
    val indicadorImovelCondominio: Int? = null,
    val imovelPerfil: Int? = null,
    val localidade: Int? = null,
    val quadra: Int? = null,
    val setorComercial: Int? = null,
    val ligacaoAguaSituacao: Int? = null,
    val ligacaoEsgotoSituacao: Int? = null,
    val logradouroCep: Int? = null,
    val logradouroBairro: Int? = null,
    val enderecoReferencia: Int? = null,
    val perimetroInicial: Int? = null,
    val perimetroFinal: Int? = null,
    val consumoTarifa: Int? = null

)

