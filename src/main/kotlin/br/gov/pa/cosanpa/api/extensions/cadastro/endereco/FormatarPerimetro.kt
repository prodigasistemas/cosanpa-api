package br.gov.pa.cosanpa.api.extensions.cadastro.endereco

import br.gov.pa.cosanpa.api.dominio.cadastro.endereco.Logradouro

fun Logradouro.formatarPerimetro(): String {
    var retorno = ""

    logradouroTipo?.let { retorno += it.descricaoAbreviada }

    logradouroTitulo?.let { retorno += " " + it.descricaoAbreviada }

    return "$retorno$nome"
}