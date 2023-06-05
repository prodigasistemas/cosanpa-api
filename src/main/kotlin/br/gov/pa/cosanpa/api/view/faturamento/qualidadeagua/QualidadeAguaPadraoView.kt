package br.gov.pa.cosanpa.api.view.faturamento.qualidadeagua

data class QualidadeAguaPadraoView(
    val id: Int,
    val descricaoPadraoTurbidez: String,
    val descricaoPadraoPh: String,
    val descricaoPadraoCor: String,
    val descricaoPadraoCloro: String,
    val descricaoPadraoFluor: String,
    val descricaoPadraoFerro: String,
    val descricaoPadraoColiformesTotais: String,
    val descricaoPadraoColiformesFecais: String,
    val descricaoNitrato: String,
    val descricaoPadraoColiformesTermotolerantes: String,
)
