package br.gov.pa.cosanpa.api.view.endereco

import br.gov.pa.cosanpa.api.dominio.cadastro.imovel.Imovel
import br.gov.pa.cosanpa.api.dominio.cadastro.cliente.ClienteEndereco
import br.gov.pa.cosanpa.api.dominio.cadastro.endereco.*
import br.gov.pa.cosanpa.api.dominio.cadastro.geografico.Bairro
import br.gov.pa.cosanpa.api.dominio.cadastro.geografico.Municipio
import br.gov.pa.cosanpa.api.dominio.cadastro.geografico.UnidadeFederacao

data class DadosEndereco(
    val logradouro: Logradouro?,
    val logradouroTipo: LogradouroTipo?,
    val logradouroTitulo: LogradouroTitulo?,
    val bairro: Bairro?,
    val municipio: Municipio?,
    val unidadeFederacao: UnidadeFederacao?,
    val enderecoReferencia: EnderecoReferencia?,
    val cep: Cep?,
    val imovel: Imovel? = null,
    val clienteEndereco: ClienteEndereco? = null,
    val logradouroCep: LogradouroCep?,
    val logradouroBairro: LogradouroBairro?,
    val perimetroInicial: Logradouro?,
    val perimetroFinal: Logradouro?,
)
