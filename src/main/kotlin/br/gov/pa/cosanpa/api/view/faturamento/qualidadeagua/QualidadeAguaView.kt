package br.gov.pa.cosanpa.api.view.faturamento.qualidadeagua

import java.math.BigDecimal

data class QualidadeAguaView (
    val id: Int,
    val anoMesReferencia: Int,
    val numeroIndiceTurbidez: BigDecimal,
    val numeroCloroResidual: BigDecimal,
    val numeroIndicePh: BigDecimal,
    val numeroIndiceCor: BigDecimal,
    val numeroIndiceFluor: BigDecimal,
    val numeroIndiceFerro: BigDecimal,
    val numeroIndiceColiformesTotais: BigDecimal,
    val numeroIndiceColiformesFecais: BigDecimal,
    val numeroNitrato: BigDecimal,
    val numeroIndiceAlcalinidade: BigDecimal,
    val quantidadeTurbidezExigidas: Int,
    val quantidadeTurbidezAnalisadas: Int,
    val quantidadeTurbidezConforme: Int,
    val quantidadeCorExigidas: Int,
    val quantidadeCorAnalisadas: Int,
    val quantidadeCorConforme: Int,
    val quantidadeCloroExigidas: Int,
    val quantidadeCloroAnalisadas: Int,
    val quantidadeCloroConforme: Int,
    val quantidadeFluorExigidas: Int,
    val quantidadeFluorAnalisadas: Int,
    val quantidadeFluorConforme: Int,
    val quantidadeColiformesTotaisExigidas: Int,
    val quantidadeColiformesTotaisAnalisadas: Int,
    val quantidadeColiformesTotaisConforme: Int,
    val quantidadeColiformesFecaisExigidas: Int,
    val quantidadeColiformesFecaisAnalisadas: Int,
    val quantidadeColiformesFecaisConforme: Int,
    val quantidadeColiformesTermotolerantesExigidas: Int,
    val numeroIndiceColiformesTermotolerantes: Int,
    val quantidadeColiformesTermotolerantesAnalisadas: Int,
    val quantidadeColiformesTermotolerantesConforme: Int,
    val quantidadeAlcalinidadeExigidas: Int,
    val quantidadeAlcalinidadeAnalisadas: Int,
    val quantidadeAlcalinidadeConforme: Int
)
