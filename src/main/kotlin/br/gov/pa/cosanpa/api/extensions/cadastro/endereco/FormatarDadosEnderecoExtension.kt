package br.gov.pa.cosanpa.api.extensions.cadastro.endereco

import br.gov.pa.cosanpa.api.view.endereco.DadosEndereco

fun DadosEndereco.formatarEndereco(): String {
    var endereco = ""

    // verifica se o logradouro do imovel eh diferente de null
    logradouroCep?.logradouro?.let { logradouro ->
        if (logradouro.id != 0) {
            logradouroTipo?.let { logradouroTipo ->
                if (logradouroTipo.descricao.isNotEmpty()) endereco += " " + logradouroTipo.descricao.trim()
            }

            logradouroTitulo?.let { logradouroTitulo ->
                if (logradouroTitulo.descricao.isNotEmpty()) endereco = " " + logradouroTitulo.descricao.trim()
            }

            endereco += " " + logradouroCep.logradouro.nome.trim()

            enderecoReferencia?.let {enderecoReferencia ->
                if (enderecoReferencia.descricao.isNotEmpty()) endereco += " " + enderecoReferencia.descricao
            }

            imovel?.let {
                if(!it.numero.isNullOrEmpty()) endereco += " " + it.numero.trim()
                if (!it.complementoEndereco.isNullOrEmpty()) endereco += " " + it.complementoEndereco.trim()
            }

            clienteEndereco?.let {
                if(it.numero.isNotEmpty()) endereco += " " + it.numero.trim()
                if (it.complementoEndereco.isNotEmpty()) endereco += " " + it.complementoEndereco.trim()
            }

            bairro?.let { bairro ->
                if (bairro.id != 0) endereco += " - " + bairro.nome.trim()

                municipio?.let { municipio ->
                    if (municipio.id != 0) endereco += " " + municipio.nome.trim()
                }

                unidadeFederacao?.let { uf ->
                    if (uf.id != 0) endereco += " " + uf.sigla.trim()
                }
            }

            cep?.let { cep ->  endereco += " " + cep.formataCep() }

            perimetroInicial?.let { endereco += " ENTRE " + perimetroInicial.formatarPerimetro() + " E " + perimetroFinal?.formatarPerimetro()}

        }
    }
    return endereco
}