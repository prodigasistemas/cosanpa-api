package br.gov.pa.cosanpa.api.extensions.util

fun String.adicionarZerosEsqueda(tamanho: Int): String {
    var zeros = ""

    for (a in 0 until tamanho - length) {
        zeros += "0"
    }

    return zeros + this
}