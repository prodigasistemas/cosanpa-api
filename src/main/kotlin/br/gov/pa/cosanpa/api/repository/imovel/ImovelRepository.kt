package br.gov.pa.cosanpa.api.repository.imovel

import br.gov.pa.cosanpa.api.dominio.cadastro.Imovel
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface ImovelRepository : JpaRepository<Imovel, Int> {

//    @Query(
//        value = "select logradouro.nome," +  // 0
//                " logradouroTipo.descricao," +  // 1
//                " logradouroTitulo.descricao," +  // 2
//                " bairro.id," +  // 3
//                " bairro.nome," +  // 4
//                " municipio.id," +  // 5
//                " municipio.nome," +  // 6
//                " unidadeFederacao.id," +  // 7
//                " unidadeFederacao.sigla," +  // 8
//                " enderecoReferencia.descricao," +  // 9
//                " cep.id," +  // 10
//                " cep.logradouro," +  // 11
//                " cep.descricaoTipoLogradouro," +  // 12
//                " cep.bairro," +  // 13
//                " cep.municipio," +  // 14
//                " cep.sigla, " +  // 15
//                " cep.codigo, " +  // 16
//                " imovel.numero," +  // 17
//                " imovel.complementoEndereco ," +  // 18
//                " logradouro.id, " +  // 19
//                " logradouroCep.id," +  // 20
//                " logradouroBairro.id," +  // 21
//                " logradouroTipo.descricaoAbreviada," +  // 22
//                " logradouroTitulo.descricaoAbreviada," +  // 23
//                " enderecoReferencia.descricaoAbreviada, " +  // 24
//                " enderecoReferencia.id, " +  // 25
//                " perimetroInicial.id, " +  // 26
//                " perimetroInicial.nome, " +  // 27
//                " logradouroTipoPerimetroInicial.descricaoAbreviada, " +  // 28
//                " logradouroTituloPerimetroInicial.descricaoAbreviada, " +  // 29
//                " perimetroFinal.id, " +  // 30
//                " perimetroFinal.nome, " +  // 31
//                " logradouroTipoPerimetroFinal.descricaoAbreviada, " +  // 32
//                " logradouroTituloPerimetroFinal.descricaoAbreviada " // 33
//                + "from Imovel imovel "
//                + "left join imovel.logradouroCep logradouroCep "
//                + "left join logradouroCep.logradouro logradouro "
//                + "left join logradouro.logradouroTipo logradouroTipo "
//                + "left join logradouro.logradouroTitulo logradouroTitulo "
//                + "left join imovel.logradouroBairro logradouroBairro "
//                + "left join logradouroBairro.bairro bairro "
//                + "left join imovel.setorComercial setorComercial "
//                + "left join logradouro.municipio municipio "
//                + "left join municipio.unidadeFederacao unidadeFederacao "
//                + "left join imovel.enderecoReferencia enderecoReferencia "
//                + "left join logradouroCep.cep cep "
//                + "left join imovel.perimetroInicial perimetroInicial "
//                + "left join perimetroInicial.logradouroTipo logradouroTipoPerimetroInicial "
//                + "left join perimetroInicial.logradouroTitulo logradouroTituloPerimetroInicial "
//                + "left join imovel.perimetroFinal perimetroFinal "
//                + "left join perimetroFinal.logradouroTipo logradouroTipoPerimetroFinal "
//                + "left join perimetroFinal.logradouroTitulo logradouroTituloPerimetroFinal "
//                + "where imovel.id = :id"
//    )
//    fun pesquisarEnderecoFormatado(id: Int) : Collection<*>

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
    fun pesquisarEnderecoFormatado(id: Int) : Imovel

}
