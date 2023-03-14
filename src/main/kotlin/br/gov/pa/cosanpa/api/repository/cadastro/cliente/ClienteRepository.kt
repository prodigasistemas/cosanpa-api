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
                " FROM ClienteEndereco clienteEndereco " +
                " LEFT JOIN clienteEndereco.logradouroCep logradouroCep " +
                " LEFT JOIN logradouroCep.cep cep " +
                " LEFT JOIN logradouroCep.logradouro logradouro " +
                " LEFT JOIN logradouro.logradouroTipo logradouroTipo " +
                " LEFT JOIN logradouro.logradouroTitulo logradouroTitulo " +
                " LEFT JOIN clienteEndereco.logradouroBairro logradouroBairro " +
                " LEFT JOIN logradouroBairro.bairro bairro " +
                " LEFT JOIN logradouro.municipio municipio " +
                " LEFT JOIN bairro.municipio municipioBairro " +
                " LEFT JOIN municipio.unidadeFederacao unidadeFederacao " +
                " LEFT JOIN clienteEndereco.enderecoReferencia enderecoReferencia " +
                " LEFT JOIN municipioBairro.unidadeFederacao unidadeFederacaoBairro " +
                " LEFT JOIN clienteEndereco.perimetroInicial perimetroInicial " +
                " LEFT JOIN perimetroInicial.logradouroTipo logradouroTipoPerimetroInicial " +
                " LEFT JOIN perimetroInicial.logradouroTitulo logradouroTituloPerimetroInicial " +
                " LEFT JOIN clienteEndereco.perimetroFinal perimetroFinal " +
                " LEFT JOIN perimetroFinal.logradouroTipo logradouroTipoPerimetroFinal " +
                " LEFT JOIN perimetroFinal.logradouroTitulo logradouroTituloPerimetroFinal " +
                " INNER JOIN clienteEndereco.cliente cliente " +
                " WHERE cliente.id = :idCliente AND " +
                " clienteEndereco.indicadorEnderecoCorrespondencia = :indicadorEnderecoCorrespondencia"
    )
    fun obterDadosEnderecoCorrespondencia(idCliente: Int, indicadorEnderecoCorrespondencia: Short) : EnderecoDTO
}
