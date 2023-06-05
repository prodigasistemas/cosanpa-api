package br.gov.pa.cosanpa.api.view.micromedicao.hidrometro

data class HidrometroInstalacaoHistoricoView(
    val id: Int,
    val dataInstalacao: String,
    val numeroLeituraInstalacao: Int,
    val dataRetirada: String,
)
