package br.gov.pa.cosanpa.api.repository.cadastro.cliente

import br.gov.pa.cosanpa.api.dominio.cadastro.cliente.Cliente
import br.gov.pa.cosanpa.api.dominio.cadastro.cliente.ClienteEndereco
import br.gov.pa.cosanpa.api.dto.cadastro.endereco.EnderecoDTO
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import kotlin.reflect.jvm.internal.impl.renderer.ClassifierNamePolicy.SHORT

interface ClienteRepository : JpaRepository<Cliente, Int> {
    @Query(
        value = "SELECT new br.gov.pa.cosanpa.api.dto.cadastro.endereco.EnderecoDTO" +
                " (logradouroTipo.descricao as logradouroTipoDescricao, " +
                " logradouroTitulo.descricao as logradouroTituloDescricao, " +
                " logradouro.nome as logradouroNome, " +
                " enderecoReferencia.descricao as enderecoReferenciaDescricao, " +
                " clienteEndereco.numero as numeroImovel, " +
                " clienteEndereco.complementoEndereco as complementoEndereco, " +
                " bairro.nome as bairroNome, " +
                " municipio.nome as municipioNome, " +
                " unidadeFederacao.sigla as unidadeFederacaoSigla, " +
                " cep.codigo as cepCodigo, " +
                " logradouroTipoPerimetroInicial.descricaoAbreviada as perimetroInicialLogradouroTipoDescricaoAbreviada, " +
                " logradouroTituloPerimetroInicial.descricaoAbreviada as perimetroInicialLogradouroTituloDescricaoAbreviada, " +
                " perimetroInicial.nome as perimetroInicialNome, " +
                " logradouroTipoPerimetroFinal.descricaoAbreviada as perimetroFinalLogradouroTipoDescricaoAbreviada, " +
                " logradouroTituloPerimetroFinal.descricaoAbreviada as perimetroFinalLogradouroTituloDescricaoAbreviada, " +
                " perimetroFinal.nome as perimetroFinalNome)  " +
                " from ClienteEndereco clienteEndereco " +
                " left join clienteEndereco.logradouroCep logradouroCep " +
                " left join logradouroCep.cep cep " +
                " left join logradouroCep.logradouro logradouro " +
                " left join logradouro.logradouroTipo logradouroTipo " +
                " left join logradouro.logradouroTitulo logradouroTitulo " +
                " left join clienteEndereco.logradouroBairro logradouroBairro " +
                " left join logradouroBairro.bairro bairro " +
                " left join logradouro.municipio municipio " +
                " left join bairro.municipio municipioBairro " +
                " left join municipio.unidadeFederacao unidadeFederacao " +
                " left join clienteEndereco.enderecoReferencia enderecoReferencia " +
                " left join municipioBairro.unidadeFederacao unidadeFederacaoBairro " +
                " left join clienteEndereco.perimetroInicial perimetroInicial " +
                " left join perimetroInicial.logradouroTipo logradouroTipoPerimetroInicial " +
                " left join perimetroInicial.logradouroTitulo logradouroTituloPerimetroInicial " +
                " left join clienteEndereco.perimetroFinal perimetroFinal " +
                " left join perimetroFinal.logradouroTipo logradouroTipoPerimetroFinal " +
                " left join perimetroFinal.logradouroTitulo logradouroTituloPerimetroFinal " +
                " inner join clienteEndereco.cliente cliente " +
                " where cliente.id = :idCliente AND " +
                " clienteEndereco.indicadorEnderecoCorrespondencia = :indicadorEnderecoCorrespondencia"
    )
    fun obterDadosEnderecoCorrespondencia(idCliente: Int, indicadorEnderecoCorrespondencia: Short) : EnderecoDTO
}
