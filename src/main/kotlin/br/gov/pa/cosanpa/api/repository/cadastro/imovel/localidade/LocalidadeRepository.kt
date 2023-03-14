package br.gov.pa.cosanpa.api.repository.cadastro.imovel.localidade

import br.gov.pa.cosanpa.api.dominio.cadastro.localidade.Localidade
import br.gov.pa.cosanpa.api.dto.cadastro.endereco.EnderecoDTO
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface LocalidadeRepository : JpaRepository<Localidade, Int> {

    @Query(
        value = "SELECT new br.gov.pa.cosanpa.api.dto.cadastro.endereco.EnderecoDTO  " +
                "  (logradouroTipo.descricao as logradouroTipoDescricao,   " +
                "  logradouroTitulo.descricao as logradouroTituloDescricao,   " +
                "  logradouro.nome as logradouroNome,   " +
                "  enderecoReferencia.descricao as enderecoReferenciaDescricao,   " +
                "  localidade.numero as numeroImovel,   " +
                "  localidade.complementoEndereco as complementoEndereco,   " +
                "  bairro.nome as bairroNome,   " +
                "  municipio.nome as municipioNome,   " +
                "  unidadeFederacao.sigla as unidadeFederacaoSigla,   " +
                "  cep.codigo as cepCodigo) " +
                "  FROM Localidade localidade   " +
                "  LEFT JOIN localidade.logradouroCep logradouroCep   " +
                "  LEFT JOIN logradouroCep.cep cep   " +
                "  LEFT JOIN logradouroCep.logradouro logradouro   " +
                "  LEFT JOIN logradouro.logradouroTipo logradouroTipo   " +
                "  LEFT JOIN logradouro.logradouroTitulo logradouroTitulo   " +
                "  LEFT JOIN localidade.logradouroBairro logradouroBairro   " +
                "  LEFT JOIN logradouroBairro.bairro bairro   " +
                "  LEFT JOIN logradouro.municipio municipio   " +
                "  LEFT JOIN bairro.municipio municipioBairro   " +
                "  LEFT JOIN municipio.unidadeFederacao unidadeFederacao   " +
                "  LEFT JOIN localidade.enderecoReferencia enderecoReferencia   " +
                "  LEFT JOIN municipioBairro.unidadeFederacao unidadeFederacaoBairro   " +
                "  WHERE localidade.id = :idLocalidade"
    )
    fun obterDadosEnderecoAtendimento(idLocalidade: Int): EnderecoDTO
}
