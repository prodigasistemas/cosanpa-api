package br.gov.pa.cosanpa.api.repository.cadastro.imovel

import br.gov.pa.cosanpa.api.dominio.cadastro.imovel.Imovel
import br.gov.pa.cosanpa.api.dto.cadastro.imovel.CategoriaDTO
import br.gov.pa.cosanpa.api.dto.cadastro.imovel.ImovelDTO
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface ImovelRepository : JpaRepository<Imovel, Int> {

    @Query(
        value = "select imovel "
                + "from Imovel imovel "
                + "left join imovel.logradouroCep logradouroCep "
                + "left join logradouroCep.logradouro logradouro "
                + "left join logradouro.logradouroTipo logradouroTipo "
                + "left join logradouro.logradouroTitulo logradouroTitulo "
                + "left join imovel.logradouroBairro logradouroBairro "
                + "left join logradouroBairro.bairro bairro "
                + "left join imovel.setorComercial setorComercial "
                + "left join logradouro.municipio municipio "
                + "left join municipio.unidadeFederacao unidadeFederacao "
                + "left join imovel.enderecoReferencia enderecoReferencia "
                + "left join logradouroCep.cep cep "
                + "left join imovel.perimetroInicial perimetroInicial "
                + "left join perimetroInicial.logradouroTipo logradouroTipoPerimetroInicial "
                + "left join perimetroInicial.logradouroTitulo logradouroTituloPerimetroInicial "
                + "left join imovel.perimetroFinal perimetroFinal "
                + "left join perimetroFinal.logradouroTipo logradouroTipoPerimetroFinal "
                + "left join perimetroFinal.logradouroTitulo logradouroTituloPerimetroFinal "
                + "where imovel.id = :id"
    )
    fun obterDadosEndereco(id: Int): Imovel


    @Query(
        value = "select new br.gov.pa.cosanpa.api.dto.cadastro.imovel.ImovelDTO(imovel.id as id, consumoTarifa.id as consumoTarifa) " +
                "from Imovel imovel " +
                "left join imovel.consumoTarifa consumoTarifa " +
                "where imovel.id = :id"
    )
    fun obterConsumoTarifa(id: Int): ImovelDTO

    @Query(
        value = " select new br.gov.pa.cosanpa.api.dto.cadastro.imovel.CategoriaDTO " +
                " (c.id as id, " +
                "   c.descricao as descricao, " +
                "       c.fatorEconomias as fatorEconomias, " +
                "           sum(isb.quantidadeEconomias) as quantidadeEconomias) " +
                " from ImovelSubcategoria isb  " +
                " inner join isb.subcategoria sb  " +
                " inner join sb.categoria c  " +
                " inner join c.categoriaTipo ct  " +
                " where isb.imovel.id = :id " +
                " group by c.id, c.descricao, c.fatorEconomias, c.categoriaTipo.id, c.categoriaTipo.descricao " +
                " order by c.id "
    )
    fun obterCategoriasPorImovel(id: Int): List<CategoriaDTO>
}
