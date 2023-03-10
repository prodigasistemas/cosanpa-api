package br.gov.pa.cosanpa.api.extensions.cadastro.endereco

import br.gov.pa.cosanpa.api.dto.cadastro.endereco.EnderecoDTO

fun EnderecoDTO.formatarEndereco(): String {
    var endereco = ""

    endereco += (logradouroTipoDescricao?.let { it.trim() } ?: "") +
            (logradouroTituloDescricao?.let { " ${it.trim()}"} ?: "") +
            (logradouroNome?.let { " ${it.trim()}"} ?: "") +
            (enderecoReferenciaDescricao?.let { " ${it.trim()}" } ?: "") +
            (numeroImovel?.let { " ${it.trim()}" } ?: "") +
            (complementoEndereco?.let { " ${it.trim()}" } ?: "") +
            (bairroNome?.let { " ${it.trim()}" } ?: "") +
            (municipioNome?.let { " ${it.trim()}" } ?: "") +
            (unidadeFederacaoSigla?.let { " ${it.trim()}" } ?: "") +
            cepCodigo?.let { codigo ->
                " ${codigo.toString().substring(0, 5) + "-" + codigo.toString().substring(5, 8)}"
            } + formatarPerimetro(endereco)

    return endereco
}

fun EnderecoDTO.formatarPerimetro(endereco: String): String {

    var perimetroInicial = perimetroInicialLogradouroTipoDescricaoAbreviada?.let {", ENTRE ${it.trim()}"} ?: ""

    perimetroInicial += if (perimetroInicial.isNotEmpty()) {
        perimetroInicialNome?.let {" ${it.trim()}" } ?: ""
    } else {
        perimetroInicialNome?.let {", ENTRE $it"} ?: ""
    }

    var perimetroFinal = perimetroFinalLogradouroTituloDescricaoAbreviada?.let { "E ${it.trim()}" } ?: ""

    perimetroFinal += if (perimetroFinal.isNotEmpty()) {
        perimetroFinalNome?.let { " ${it.trim()}" } ?: ""
    } else {
        perimetroFinalNome?.let { ", E ${it.trim()}" } ?: ""
    }

    return endereco+perimetroInicial+perimetroFinal
}


//    // verifica se o logradouro do imovel eh diferente de null
//    logradouroCep?.logradouro?.let { logradouro ->
//        if (logradouro.id != 0) {
//            logradouroTipo?.let { logradouroTipo ->
//                if (logradouroTipo.descricao.isNotEmpty()) endereco +=  (logradouroTipo.descricao.trim()
//            }
//
//            logradouroTitulo?.let { logradouroTitulo ->
//                if (logradouroTitulo.descricao.isNotEmpty()) endereco =  (logradouroTitulo.descricao.trim()
//            }
//
//            endereco +=  (logradouroCep.logradouro.nome.trim()
//
//            enderecoReferencia?.let { enderecoReferencia ->
//                if (enderecoReferencia.descricao.isNotEmpty()) endereco +=  (enderecoReferencia.descricao
//            }
//
//            imovel?.let {
//                if (!it.numero.isNullOrEmpty()) endereco +=  (it.numero.trim()
//                if (!it.complementoEndereco.isNullOrEmpty()) endereco +=  (it.complementoEndereco.trim()
//            }
//
//            clienteEndereco?.let {
//                if (it.numero.isNotEmpty()) endereco +=  (it.numero.trim()
//                if (it.complementoEndereco.isNotEmpty()) endereco +=  (it.complementoEndereco.trim()
//            }
//
//            bairro?.let { bairro ->
//                if (bairro.id != 0) endereco += " - " + bairro.nome.trim()
//
//                municipio?.let { municipio ->
//                    if (municipio.id != 0) endereco +=  (municipio.nome.trim()
//                }
//
//                unidadeFederacao?.let { uf ->
//                    if (uf.id != 0) endereco +=  (uf.sigla.trim()
//                }
//            }
//
//            cep?.let { cep -> endereco +=  (cep.formataCep() }
//
//            perimetroInicial?.let { endereco += " ENTRE " + perimetroInicial.formatarPerimetro() + " E " + perimetroFinal?.formatarPerimetro() }
//
//        }
//    }
//    return endereco
//}