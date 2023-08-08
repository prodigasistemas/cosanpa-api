package br.gov.pa.cosanpa.api.extensions.cadastro.endereco

import br.gov.pa.cosanpa.api.dto.cadastro.endereco.EnderecoDTO

fun EnderecoDTO.formatarEndereco(): String {
    return  (logradouroTipoDescricao?.trim() ?: "") +
            (logradouroTituloDescricao?.let { " ${it.trim()}"} ?: "") +
            (logradouroNome?.let { " ${it.trim()}"} ?: "") +
            (enderecoReferenciaDescricao?.let { " ${it.trim()}" } ?: "") +
            (numeroImovel?.let { " ${it.trim()}" } ?: "") +
            (complementoEndereco?.let { " ${it.trim()}" } ?: "") +
            (bairroNome?.let { " ${it.trim()}" } ?: "") +
            (municipioNome?.let { " ${it.trim()}" } ?: "") +
            (unidadeFederacaoSigla?.let { " ${it.trim()}" } ?: "") +
            (cepCodigo?.let { codigo ->
                " ${codigo.toString().substring(0, 5) + "-" + codigo.toString().substring(5, 8)}"
            }) + formatarPerimetro()


}

fun EnderecoDTO.formatarPerimetro(): String {

    var perimetroInicial = perimetroInicialLogradouroTipoDescricaoAbreviada?.let {", ENTRE ${it.trim()}"} ?: ""

    perimetroInicial += if (perimetroInicial.isNotEmpty()) {
        perimetroInicialNome?.let {" ${it.trim()}" } ?: ""
    } else {
        perimetroInicialNome?.let {", ENTRE $it"} ?: ""
    }

    var perimetroFinal = perimetroFinalLogradouroTituloDescricaoAbreviada?.let { " E ${it.trim()}" } ?: ""

    perimetroFinal += if (perimetroFinal.isNotEmpty()) {
        perimetroFinalNome?.let { " ${it.trim()}" } ?: ""
    } else {
        perimetroFinalNome?.let { " E ${it.trim()}" } ?: ""
    }

    return (perimetroInicial + perimetroFinal)
}