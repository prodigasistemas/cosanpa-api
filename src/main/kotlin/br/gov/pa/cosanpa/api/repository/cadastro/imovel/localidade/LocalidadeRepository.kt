package br.gov.pa.cosanpa.api.repository.cadastro.imovel.localidade

import br.gov.pa.cosanpa.api.dominio.cadastro.localidade.Localidade
import br.gov.pa.cosanpa.api.dto.Dto
import br.gov.pa.cosanpa.api.dto.cadastro.endereco.EnderecoDTO
import br.gov.pa.cosanpa.api.dto.cadastro.imovel.localidade.LocalidadeDTO
import br.gov.pa.cosanpa.api.dto.cadastro.imovel.endereco.MunicipioDTO
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface LocalidadeRepository : JpaRepository<Localidade, Int> {

    @Query(
        value = "SELECT new br.gov.pa.cosanpa.api.dto.cadastro.endereco.EnderecoDTO ( " +
                " logradouroTipo.descricao as logradouroTipoDescricao, " +
                "  logradouroTitulo.descricao as logradouroTituloDescricao, " +
                "  logradouro.nome as logradouroNome, " +
                "  enderecoReferencia.descricao as enderecoReferenciaDescricao, " +
                "  localidade.numero as numeroImovel, " +
                "  localidade.complementoEndereco as complementoEndereco, " +
                "  bairro.nome as bairroNome, " +
                "  municipio.nome as municipioNome, " +
                "  unidadeFederacao.sigla as unidadeFederacaoSigla, " +
                "  cep.codigo as cepCodigo ) " +
                "  FROM Localidade localidade " +
                "  LEFT JOIN localidade.logradouroCep logradouroCep " +
                "  LEFT JOIN logradouroCep.cep cep " +
                "  LEFT JOIN logradouroCep.logradouro logradouro " +
                "  LEFT JOIN logradouro.logradouroTipo logradouroTipo " +
                "  LEFT JOIN logradouro.logradouroTitulo logradouroTitulo " +
                "  LEFT JOIN localidade.logradouroBairro logradouroBairro " +
                "  LEFT JOIN logradouroBairro.bairro bairro " +
                "  LEFT JOIN logradouro.municipio municipio " +
                "  LEFT JOIN bairro.municipio municipioBairro " +
                "  LEFT JOIN municipio.unidadeFederacao unidadeFederacao " +
                "  LEFT JOIN localidade.enderecoReferencia enderecoReferencia " +
                "  LEFT JOIN municipioBairro.unidadeFederacao unidadeFederacaoBairro " +
                "  WHERE localidade.id = :idLocalidade"
    )
    fun obterDadosEnderecoAtendimento(idLocalidade: Int): EnderecoDTO
    
    @Query(
        value = "SELECT new br.gov.pa.cosanpa.api.dto.cadastro.imovel.localidade.LocalidadeDTO(" +
                " loca.id as id, " +
                " loca.descricao as descricao, " +
                " loca.numero as numero, " +
                " loca.complementoEndereco as complementoEndereco, " +
                " loca.fone as fone, " +
                " lgdCep.id as idLogradouroCep, " +
                " lgdBairro.id as idLogradouroBairro, " +
                " ef.id as idEnderecoReferencia, " +
                " gr.id as idGerenciaRegional, " +
                " muni.id as idMunicipio) " +
                " FROM Localidade loca " +
                " LEFT JOIN loca.enderecoReferencia ef " +
                " LEFT JOIN loca.gerenciaRegional gr " +
                " LEFT JOIN loca.logradouroCep lgdCep " +
                " LEFT JOIN loca.logradouroBairro lgdBairro " +
                " LEFT JOIN loca.municipio muni " +
                " WHERE loca.id = :idLocalidade "
    )
    fun obterDadosLocalidade(idLocalidade: Int): LocalidadeDTO

    @Query(
        value = " SELECT new br.gov.pa.cosanpa.api.dto.Dto( " +
                " gr.id as id, " +
                " gr.nome as descricao) " +
                " FROM GerenciaRegional gr " +
                " WHERE gr.id = :idGerenciaRegional"
    )
    fun obterDadosGerenciaRegional(idGerenciaRegional: Int) : Dto

    @Query(
        value = " SELECT new br.gov.pa.cosanpa.api.dto.cadastro.imovel.endereco.MunicipioDTO( " +
                " muni.id as id, " +
                " muni.nome as nome, " +
                " muni.ddd as ddd, " +
                " muni.unidadeFederacao.id as idUnidadeFederacao) " +
                " FROM Municipio muni " +
                " WHERE muni.id = :idMunicipio"
    )
    fun obterDadosMunicipio(idMunicipio: Int): MunicipioDTO
}
