package br.gov.pa.cosanpa.api.view.rota

data class RotaView(
    val id: Int,
    val grupo: Int,
    val localidade: Int,
    val codigoSetorComercial: Int,
    val codigoRota: Int,
    val referencia: Int
)
