package br.gov.pa.cosanpa.api.extensions.util

import java.math.BigDecimal
import java.math.RoundingMode
import java.time.LocalDate
import java.time.format.DateTimeFormatter

fun String.adicionarZerosEsquerda(tamanho: Int): String {
    var zeros = ""

    for (a in 0 until tamanho - length) {
        zeros += "0"
    }

    return zeros + this

}

fun Int.converterReferenciaParaLocalDate(): LocalDate {
    val dataFormatacao = "" + this

    val ano = dataFormatacao.substring(0, 4).toInt()
    val mes = dataFormatacao.substring(4, 6).toInt()

    return LocalDate.of(ano, mes, 1)
}

fun LocalDate.conveterLocalDateParaReferencia(): Int {
    val data = this.format(DateTimeFormatter.BASIC_ISO_DATE) // yyyyMMdd
    return data.substring(0, 6).toInt()
}

fun Any?.isNullOuVazio(): Boolean {
    return this == null || this == ""
}

fun Int.subtrairAnos(qtAnos: Int): Int = converterReferenciaParaLocalDate()
    .minusYears(qtAnos.toLong())
    .conveterLocalDateParaReferencia()
    .toString()
    .substring(0, 4)
    .toInt()

fun BigDecimal.arredondar(): Int = setScale(0, RoundingMode.CEILING).toInt()
