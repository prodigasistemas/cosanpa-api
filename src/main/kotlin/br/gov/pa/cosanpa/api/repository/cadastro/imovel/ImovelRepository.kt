package br.gov.pa.cosanpa.api.repository.cadastro.imovel

import br.gov.pa.cosanpa.api.dominio.cadastro.imovel.Imovel
import br.gov.pa.cosanpa.api.dto.cadastro.endereco.EnderecoDTO
import br.gov.pa.cosanpa.api.dto.cadastro.imovel.CategoriaDTO
import br.gov.pa.cosanpa.api.dto.cadastro.imovel.ImovelDTO
import br.gov.pa.cosanpa.api.dto.cadastro.imovel.InscricaoDTO
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface ImovelRepository : JpaRepository<Imovel, Int> {

    @Query(
        value = "SELECT new br.gov.pa.cosanpa.api.dto.cadastro.endereco.EnderecoDTO" +
                " (logradouroTipo.descricao as logradouroTipoDescricao, " +
                " logradouroTitulo.descricao as logradouroTituloDescricao, " +
                " logradouro.nome as logradouroNome, " +
                " enderecoReferencia.descricao as enderecoReferenciaDescricao, " +
                " imovel.numero as numeroImovel, " +
                " imovel.complementoEndereco as complementoEndereco, " +
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
                " FROM Imovel imovel " +
                " LEFT JOIN imovel.logradouroCep logradouroCep " +
                " LEFT JOIN logradouroCep.cep cep " +
                " LEFT JOIN logradouroCep.logradouro logradouro " +
                " LEFT JOIN logradouro.logradouroTipo logradouroTipo " +
                " LEFT JOIN logradouro.logradouroTitulo logradouroTitulo " +
                " LEFT JOIN imovel.logradouroBairro logradouroBairro " +
                " LEFT JOIN logradouroBairro.bairro bairro " +
                " LEFT JOIN logradouro.municipio municipio " +
                " LEFT JOIN bairro.municipio municipioBairro " +
                " LEFT JOIN municipio.unidadeFederacao unidadeFederacao " +
                " LEFT JOIN imovel.enderecoReferencia enderecoReferencia " +
                " LEFT JOIN municipioBairro.unidadeFederacao unidadeFederacaoBairro " +
                " LEFT JOIN imovel.perimetroInicial perimetroInicial " +
                " LEFT JOIN perimetroInicial.logradouroTipo logradouroTipoPerimetroInicial " +
                " LEFT JOIN perimetroInicial.logradouroTitulo logradouroTituloPerimetroInicial " +
                " LEFT JOIN imovel.perimetroFinal perimetroFinal " +
                " LEFT JOIN perimetroFinal.logradouroTipo logradouroTipoPerimetroFinal " +
                " LEFT JOIN perimetroFinal.logradouroTitulo logradouroTituloPerimetroFinal " +
                " WHERE imovel.id = :id"
    )
    fun obterDadosEndereco(id: Int): EnderecoDTO


    @Query(
        value = "SELECT new br.gov.pa.cosanpa.api.dto.cadastro.imovel.ImovelDTO(imovel.id as id, consumoTarifa.id as consumoTarifa) " +
                "FROM Imovel imovel " +
                "LEFT JOIN imovel.consumoTarifa consumoTarifa " +
                "WHERE imovel.id = :id"
    )
    fun obterConsumoTarifa(id: Int): ImovelDTO

    @Query(
        value = " SELECT new br.gov.pa.cosanpa.api.dto.cadastro.imovel.CategoriaDTO " +
                " (c.id as id, " +
                "  c.descricao as descricao, " +
                "  SUM(isb.quantidadeEconomias) as quantidadeEconomias," +
                "  c.consumoAlto as consumoAlto," +
                "  c.consumoEstouro as consumoEstouro," +
                "  c.numeroConsumoMaximoEc as numeroConsumoMaximoEc," +
                "  c.mediaBaixoConsumo as mediaBaixoConsumo," +
                "  c.vezesMediaAltoConsumo as vezesMediaAltoConsumo," +
                "  c.vezesMediaEstouro as vezesMediaEstouro," +
                "  c.porcentagemMediaBaixoConsumo as porcentagemMediaBaixoConsumo) " +
                " FROM ImovelSubcategoria isb  " +
                " INNER JOIN isb.subcategoria sb  " +
                " INNER JOIN sb.categoria c  " +
                " INNER JOIN c.categoriaTipo ct  " +
                " WHERE isb.imovel.id = :id " +
                " GROUP BY c.id, c.descricao, c.consumoEstouro, c.vezesMediaEstouro," +
                " isb.imovel.id, c.consumoAlto, c.mediaBaixoConsumo, c.vezesMediaAltoConsumo," +
                " c.porcentagemMediaBaixoConsumo,c.numeroConsumoMaximoEc," +
                " c.fatorEconomias, c.categoriaTipo.id, c.categoriaTipo.descricao " +
                " ORDER BY c.id "
    )
    fun obterDadosCategoriasPorImovel(id: Int): List<CategoriaDTO>

    @Query(
            value = " SELECT new br.gov.pa.cosanpa.api.dto.cadastro.imovel.InscricaoDTO( " +
                    " i.localidade.id as localidadeId, " +
                    " i.setorComercial.codigo as setorComercialCodigo, " +
                    " i.quadra.numero as quadraNumero, " +
                    " i.lote as lote, " +
                    " i.sublote as sublote) " +
                    " FROM Imovel i " +
                    " INNER JOIN i.localidade localidade " +
                    " INNER JOIN i.setorComercial setorComercial " +
                    " INNER JOIN i.quadra quadra " +
                    " WHERE i.id = :id "
    )
    fun obterDadosInscricao(id: Int): InscricaoDTO
}
