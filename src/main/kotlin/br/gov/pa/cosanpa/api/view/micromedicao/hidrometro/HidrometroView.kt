package br.gov.pa.cosanpa.api.view.micromedicao.hidrometro

data class HidrometroView(
    val id: Int,
    val numero: String,
    val numeroDigitosLeitura: Short
)