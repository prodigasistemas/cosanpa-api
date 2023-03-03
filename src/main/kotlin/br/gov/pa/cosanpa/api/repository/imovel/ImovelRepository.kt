package br.gov.pa.cosanpa.api.repository.imovel

import br.gov.pa.cosanpa.api.dominio.cadastro.imovel.Imovel
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
    fun pesquisarEnderecoFormatado(id: Int) : Imovel

}
