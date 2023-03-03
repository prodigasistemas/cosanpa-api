package br.gov.pa.cosanpa.api.repository.cliente

import br.gov.pa.cosanpa.api.dominio.cadastro.cliente.Cliente
import br.gov.pa.cosanpa.api.dominio.cadastro.cliente.ClienteEndereco
import br.gov.pa.cosanpa.api.view.endereco.DadosEndereco
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface ClienteRepository : JpaRepository<Cliente, Int> {

    @Query(value = "select clienteEndereco " +
            "from ClienteEndereco clienteEndereco " +
            "left join clienteEndereco.logradouroCep logradouroCep " +
            "left join logradouroCep.cep cep " +
            "left join logradouroCep.logradouro logradouro " +
            "left join logradouro.logradouroTipo logradouroTipo " +
            "left join logradouro.logradouroTitulo logradouroTitulo " +
            "left join clienteEndereco.logradouroBairro logradouroBairro " +
            "left join logradouroBairro.bairro bairro " +
            "left join logradouro.municipio municipio " +
            "left join bairro.municipio municipioBairro " +
            "left join municipio.unidadeFederacao unidadeFederacao " +
            "left join clienteEndereco.enderecoReferencia enderecoReferencia " +
            "left join municipioBairro.unidadeFederacao unidadeFederacaoBairro " +
            "left join clienteEndereco.perimetroInicial perimetroInicial " +
            "left join perimetroInicial.logradouroTipo logradouroTipoPerimetroInicial " +
            "left join perimetroInicial.logradouroTitulo logradouroTituloPerimetroInicial " +
            "left join clienteEndereco.perimetroFinal perimetroFinal " +
            "left join perimetroFinal.logradouroTipo logradouroTipoPerimetroFinal " +
            "left join perimetroFinal.logradouroTitulo logradouroTituloPerimetroFinal " +
            "inner join clienteEndereco.cliente cliente " +
            "where cliente.id = :idCliente AND " +
            "clienteEndereco.indicadorEnderecoCorrespondencia = :indicadorEnderecoCorrespondencia")
    fun pesquisarEnderecoCorrespondencia(idCliente: Int, indicadorEnderecoCorrespondencia: Int) : ClienteEndereco
}
