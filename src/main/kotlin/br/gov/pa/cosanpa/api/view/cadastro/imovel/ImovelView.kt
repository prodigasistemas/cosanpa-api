package br.gov.pa.cosanpa.api.view.cadastro.imovel

data class ImovelView(
    val id: Int,
    val lote: Int,
    val sublote: Int,
    val numero: String,
    val nome: String,
    val complementoEndereco: String,
    val numeroMorador: Short,
    val indicadorImovelCondominio: Int,
    val indicadorExclusao: Short,
    val numeroSequencialRota: Int,
    val codigoDebitoAutomatico: Int,
    val indicadorImovelAreaComum: Short,
    val indicadorEnvioContaFisica: Short
)
