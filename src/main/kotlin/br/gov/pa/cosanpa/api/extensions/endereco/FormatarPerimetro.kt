package br.gov.pa.cosanpa.api.extensions.endereco

import br.gov.pa.cosanpa.api.dominio.cadastro.endereco.Logradouro

fun Logradouro.formatarPerimetro(): String {
    var retorno = ""
    retorno += logradouroTipo?.descricaoAbreviada?.let { descricaoAbreviada -> descricaoAbreviada }

    retorno += if (!retorno.isNullOrEmpty()) {
        " " + logradouroTitulo?.descricaoAbreviada?.let { descricao -> descricao }
    } else {
        logradouroTitulo?.descricaoAbreviada?.let { descricao -> descricao }
    }
    retorno += if (!retorno.isNullOrEmpty()) {
        " " + nome?.let { nome -> nome }
    } else {
        nome?.let { nome -> nome }
    }
    return retorno
}