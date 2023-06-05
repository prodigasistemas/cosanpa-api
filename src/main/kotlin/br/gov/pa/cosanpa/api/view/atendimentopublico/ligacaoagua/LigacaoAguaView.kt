package br.gov.pa.cosanpa.api.view.atendimentopublico.ligacaoagua


data class LigacaoAguaView(
    val id: Int,
    val numeroConsumoMinimoAgua: Int,
    val dataLigacao: String,
    val dataCorte: String,
    val dataReligacao: String,
    val numeroLacre: String,
)
