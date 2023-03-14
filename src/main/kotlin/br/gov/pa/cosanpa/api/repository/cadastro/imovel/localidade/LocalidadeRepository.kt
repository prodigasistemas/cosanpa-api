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
                "  from Localidade localidade   " +
                "  left join localidade.logradouroCep logradouroCep   " +
                "  left join logradouroCep.cep cep   " +
                "  left join logradouroCep.logradouro logradouro   " +
                "  left join logradouro.logradouroTipo logradouroTipo   " +
                "  left join logradouro.logradouroTitulo logradouroTitulo   " +
                "  left join localidade.logradouroBairro logradouroBairro   " +
                "  left join logradouroBairro.bairro bairro   " +
                "  left join logradouro.municipio municipio   " +
                "  left join bairro.municipio municipioBairro   " +
                "  left join municipio.unidadeFederacao unidadeFederacao   " +
                "  left join localidade.enderecoReferencia enderecoReferencia   " +
                "  left join municipioBairro.unidadeFederacao unidadeFederacaoBairro   " +
                "  where localidade.id = :idLocalidade"
    )
    fun obterDadosEnderecoAtendimento(idLocalidade: Int): EnderecoDTO
}
