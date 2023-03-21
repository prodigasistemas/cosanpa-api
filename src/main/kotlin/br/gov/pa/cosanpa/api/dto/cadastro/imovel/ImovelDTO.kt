package br.gov.pa.cosanpa.api.dto.cadastro.imovel

data class ImovelDTO(
    val id: Int? = null,
    val lote: Int? = null,
    val sublote: Int? = null,
    val numero: String? = null,
    val nome: String? = null,
    val complementoEndereco: String? = null,
    val numeroMorador: Short? = null,
    val idImovelCondominio: Int? = null,
    val indicadorImovelCondominio: Int? = null,
    val indicadorExclusao: Short? = null,
    val numeroSequencialRota: Int? = null,
    val codigoDebitoAutomatico: Int? = null,
    val indicadorImovelAreaComum: Short? = null,
    val indicadorEnvioContaFisica: Short? = null,
    val indicadorParametrosConvenio: Int? = null,
    val idImovelPerfil: Int? = null,
    val idConsumoTarifa: Int? = null
)

