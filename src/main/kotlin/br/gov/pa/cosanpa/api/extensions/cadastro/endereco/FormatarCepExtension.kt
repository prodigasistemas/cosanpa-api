package br.gov.pa.cosanpa.api.extensions.cadastro.endereco

import br.gov.pa.cosanpa.api.dominio.cadastro.endereco.Cep

fun Cep.formataCep(): String {
    var codigo = codigo.toString()

    if (codigo.length == 8) {
        codigo = codigo.substring(0, 5) + "-" + codigo.substring(5, 8)
    }

    return codigo
}