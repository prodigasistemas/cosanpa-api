package br.gov.pa.cosanpa.api.view.micromedicao.medicao

data class MedicaoHistoricoView(
    val id : Int,
    val anoMesReferencia: Int,
    val dataLeituraAnteriorFaturamento: String,
    val leituraAnteriorInformada: Int,
    val leituraAnterioFaturamento: Int,
    val dataLeituraAtualInformada: String,
    val leituraAtualInformada: Int,
    val dataLeituraAtualFaturamento: String,
    val leituraAtualFaturamento: Int,
    val consumoMedioHidrometro: Int
)
