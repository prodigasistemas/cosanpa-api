package br.gov.pa.cosanpa.api.view

data class DadosLeituraView(
    val dadosImovel: MutableMap<String, Any>,
    val dadosTarifa: MutableMap<String, Any>
)

