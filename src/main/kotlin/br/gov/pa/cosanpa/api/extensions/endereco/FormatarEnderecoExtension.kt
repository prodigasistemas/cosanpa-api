package br.gov.pa.cosanpa.api.extensions.endereco

import br.gov.pa.cosanpa.api.dominio.cadastro.Imovel

fun Imovel.formatarEndereco(): String {
    var endereco = ""

    // verifica se o logradouro do imovel eh diferente de null
   logradouroCep?.logradouro?.let { logradouro ->
       if (logradouro.id != 0) {
           logradouroCep.logradouro.logradouroTipo?.let { logradouroTipo ->
               if (logradouroTipo.descricao.isNotEmpty()) endereco += " " + logradouroTipo.descricao.trim()
           }

           logradouroCep.logradouro.logradouroTitulo?.let { logradouroTitulo ->
               if (logradouroTitulo.descricao.isNotEmpty()) endereco = " " + logradouroTitulo.descricao.trim()
           }

           endereco += logradouroCep.logradouro.nome.trim()

           enderecoReferencia?.let {enderecoReferencia ->
                if (enderecoReferencia.descricao.isNotEmpty()) endereco += " " + enderecoReferencia.descricao
           }

           numero?.let { numeroImovel ->
               if (numeroImovel.isNotEmpty()) endereco += numeroImovel.trim()
           }

           complementoEndereco?.let { enderecoComplemento ->
               if (enderecoComplemento.isNotEmpty()) endereco += " - " + enderecoComplemento.trim()
           }

           logradouroBairro?.bairro?.let { bairro ->
               if (bairro.id != 0) endereco += " - " + bairro.nome.trim()

               bairro?.municipio?.let { municipio ->
                   if (municipio.id != 0) endereco += "" + municipio.nome.trim()
               }

               bairro?.municipio?.unidadeFederacao?.let { uf ->
                   if (uf.id != 0) endereco += " " + uf.sigla.trim()
               }
           }

           logradouroCep?.cep?.let { cep ->  endereco += " " + cep.formataCep() }

           perimetroInicial?.let { perimetro -> endereco + " ENTRE " + perimetroInicial.formatarPerimetro() + " E " + perimetroFinal.formatarPerimetro()}

       }
   }
    return endereco
}