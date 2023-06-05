package br.gov.pa.cosanpa.api.repository.cadastro.imovel

import br.gov.pa.cosanpa.api.dominio.cadastro.imovel.Imovel
import br.gov.pa.cosanpa.api.dto.Dto
import br.gov.pa.cosanpa.api.dto.cadastro.cliente.ClienteImovelContaDTO
import br.gov.pa.cosanpa.api.dto.cadastro.endereco.EnderecoDTO
import br.gov.pa.cosanpa.api.dto.cadastro.imovel.*
import br.gov.pa.cosanpa.api.util.ConstantesSistema
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface ImovelRepository: JpaRepository<Imovel, Int> {

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
        value = " SELECT consumoTarifa.id " +
                " FROM Imovel imovel " +
                " INNER JOIN imovel.consumoTarifa consumoTarifa " +
                " WHERE imovel.id = :idImovel"
    )
    fun obterIdConsumoTarifaPorImovel(idImovel: Int): Int

    @Query(
        value = " SELECT new br.gov.pa.cosanpa.api.dto.cadastro.imovel.CategoriaDTO " +
                " (c.id as id, " +
                " c.descricao as descricao, " +
                " SUM(isb.quantidadeEconomias) as quantidadeEconomias," +
                " c.consumoAlto as consumoAlto," +
                " c.consumoEstouro as consumoEstouro," +
                " c.numeroConsumoMaximoEc as numeroConsumoMaximoEc," +
                " c.mediaBaixoConsumo as mediaBaixoConsumo," +
                " c.vezesMediaAltoConsumo as vezesMediaAltoConsumo," +
                " c.vezesMediaEstouro as vezesMediaEstouro," +
                " c.porcentagemMediaBaixoConsumo as porcentagemMediaBaixoConsumo, " +
                " sb.id as idSubcategoria, " +
                " sb.descricao as descricaoSubcategoria) " +
                " FROM ImovelSubcategoria isb  " +
                " INNER JOIN isb.subcategoria sb  " +
                " INNER JOIN sb.categoria c  " +
                " INNER JOIN c.categoriaTipo ct  " +
                " WHERE isb.imovel.id = :id " +
                " GROUP BY c.id, c.descricao, c.consumoEstouro, c.vezesMediaEstouro," +
                " isb.imovel.id, c.consumoAlto, c.mediaBaixoConsumo, c.vezesMediaAltoConsumo," +
                " c.porcentagemMediaBaixoConsumo,c.numeroConsumoMaximoEc," +
                " c.fatorEconomias, c.categoriaTipo.id, c.categoriaTipo.descricao, sb.id, sb.descricao " +
                " ORDER BY c.id "
    )
    fun obterColecaoDadosCategoriasPorImovel(id: Int): List<CategoriaDTO>

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

    @Query(
        value = " SELECT new br.gov.pa.cosanpa.api.dto.cadastro.imovel.SubcategoriaDTO(" +
                " sb.id as id, " +
                " sb.codigo as codigo," +
                " sb.descricao as descricao, " +
                " sum(isb.quantidadeEconomias) as quantidadeEconomias, " +
                " sb.codigoTarifaSocial as codigoTarifaSocial, " +
                " sb.numeroFatorFiscalizacao as numeroFatorFiscalizacao, " +
                " sb.indicadorTarifaConsumo as indicadorTarifaConsumo, " +
                " sb.indicadorSazonalidade as indicadorSazonalidade, " +
                " sb.descricaoAbreviada as descricaoAbreviada) " +
                " FROM ImovelSubcategoria isb " +
                " INNER JOIN isb.subcategoria sb " +
                " WHERE isb.imovel.id = :idImovel " +
                " GROUP BY sb.id, " +
                " sb.codigo,sb.descricao, " +
                " sb.codigoTarifaSocial, " +
                " sb.numeroFatorFiscalizacao, " +
                " sb.indicadorTarifaConsumo, isb.imovel.id, " +
                " sb.indicadorSazonalidade, " +
                " sb.descricaoAbreviada " +
                " HAVING isb.imovel.id = :idImovel "
    )
    fun obterColecaoDadosSubcategoriasPorImovel(idImovel: Int): List<SubcategoriaDTO>

    @Query(
        value = "SELECT new br.gov.pa.cosanpa.api.dto.cadastro.imovel.ImovelDTO(" +
                " imovel.id as id, " +
                " imovel.lote as lote, " +
                " imovel.sublote as sublote, " +
                " imovel.numero as numero, " +
                " imovel.nome as nome, " +
                " imovel.complementoEndereco as complementoEndereco, " +
                " imovel.numeroMorador as numeroMorador, " +
                " imovel.indicadorImovelCondominio as indicadorImovelCondominio, " +
                " imovel.indicadorExclusao as indicadorExclusao, " +
                " imovel.numeroSequencialRota as numeroSequencialRota, " +
                " imovel.codigoDebitoAutomatico as codigoDebitoAutomatico, " +
                " imovel.indicadorImovelAreaComum as indicadorImovelAreaComum, " +
                " imovel.indicadorEnvioContaFisica as indicadorEnvioContaFisica, " +
                " imovel.idParametrosConvenio as idParametrosConvenio, " +
                " imovel.imovelCondominio.id as idImovelCondominioImovel, " +
                " localidade.id as idLocalidade, " +
                " setorComercial.id as idSetorComercial, " +
                " quadra.id as idQuadra, " +
                " ligacaoAguaSituacao.id as idLigacaoAguaSituacao, " +
                " ligacaoEsgotoSituacao.id as idLigacaoEsgotoSituacao, " +
                " imovelPerfil.id as idImovelPerfil, " +
                " consumoTarifa.id as idConsumoTarifa, " +
                " ice.id as idImovelContaEnvio, " +
                " quadraFace.id as idQuadraFace, " +
                " pocoTipo.id as idPocoTipo, " +
                " faturamentoSituacaoTipo.id as idFaturamentoSituacaoTipo) " +
                " FROM Imovel imovel " +
                " INNER JOIN imovel.localidade localidade " +
                " INNER JOIN localidade.gerenciaRegional gerenciaRegional " +
                " INNER JOIN imovel.setorComercial setorComercial " +
                " INNER JOIN imovel.quadra quadra " +
                " INNER JOIN quadra.rota rota " +
                " INNER JOIN imovel.ligacaoAguaSituacao ligacaoAguaSituacao " +
                " INNER JOIN imovel.ligacaoEsgotoSituacao ligacaoEsgotoSituacao " +
                " INNER JOIN imovel.imovelPerfil imovelPerfil " +
                " INNER JOIN imovel.consumoTarifa consumoTarifa " +
                " LEFT JOIN imovel.imovelContaEnvio ice " +
                " LEFT JOIN imovel.quadraFace quadraFace " +
                " LEFT JOIN imovel.ligacaoAgua ligacaoAgua " +
                " LEFT JOIN imovel.ligacaoEsgoto ligacaoEsgoto " +
                " LEFT JOIN imovel.pocoTipo pocoTipo " +
                " LEFT JOIN imovel.faturamentoSituacaoTipo faturamentoSituacaoTipo " +
                " WHERE imovelPerfil.indicadorGerarDadosLeitura = ${ConstantesSistema.SIM} " +
                " AND imovel.indicadorExclusao <> ${Imovel.IMOVEL_EXCLUIDO} " +
                " AND imovel.id = :idImovel " +
                " ORDER BY imovel.indicadorImovelCondominio,localidade.id, setorComercial.codigo,quadra.numero,imovel.lote,imovel.sublote"
    )
    fun obterDadosImovelGerarDados(idImovel: Int): ImovelDTO?

    @Query(
        value = " SELECT new br.gov.pa.cosanpa.api.dto.cadastro.cliente.ClienteImovelContaDTO( " +
                " clienteImovel.id as id, " +
                " clienteImovel.indicadorNomeConta as indicadorNomeConta, " +
                " clienteImovel.dataInicioRelacao as dataInicioRelacao, " +
                " clienteImovel.dataFimRelacao as dataFimRelacao, " +
                " cliente.id as idCliente, " +
                " imovel.id as idImovel, " +
                " clienteImovel.clienteRelacaoTipo.id as idClienteRelacaoTipo) " +
                " FROM Imovel imovel " +
                " INNER JOIN imovel.clienteImoveis clienteImovel " +
                " INNER JOIN clienteImovel.cliente cliente " +
                " WHERE imovel.id = :idImovel " +
                " AND clienteImovel.dataFimRelacao IS NULL"
    )
    fun obterColecaoClienteImoveis(idImovel: Int) : List<ClienteImovelContaDTO>

    @Query(
        value = " SELECT imovel.id FROM Imovel imovel " +
                " INNER JOIN imovel.quadra q " +
                " INNER JOIN q.rota r " +
                " WHERE r.id = :idRota "
    )
    fun obterColecaoIdsImoveis(idRota: Int): List<Int>

    @Query(
        value = " SELECT new br.gov.pa.cosanpa.api.dto.Dto( " +
                " ice.id as id, " +
                " ice.descricao as descricao) " +
                " FROM ImovelContaEnvio ice " +
                " WHERE ice.id = :idImovelContaEnvio "
    )
    fun obterDadosImovelContaEnvio(idImovelContaEnvio: Int) : Dto

    @Query(
        value = " SELECT new br.gov.pa.cosanpa.api.dto.Dto( " +
                " pt.id as id, " +
                " pt.descricao as descricao) " +
                " FROM PocoTipo pt " +
                " WHERE pt.id = :idPocoTipo "
    )
    fun obterDadosPocoTipo(idPocoTipo: Int): Dto

    @Query(
        value = " SELECT grupo.id FROM Imovel imovel " +
                " INNER JOIN imovel.quadra quadra " +
                " INNER JOIN quadra.rota rota " +
                " INNER JOIN rota.grupo grupo " +
                " WHERE imovel.id = :idImovel"
    )
    fun obterGrupoFaturamentoDoImovel(idImovel: Int): Int

    @Query(
        value = " SELECT sistemaAbastecimento.id FROM Imovel imovel " +
                " LEFT JOIN imovel.quadraFace quadraFace " +
                " LEFT JOIN quadraFace.distritoOperacional " +
                " LEFT JOIN distritoOperacional.setorAbastecimento " +
                " LEFT JOIN setorAbastecimento.sistemaAbastecimento sistemaAbastecimento " +
                " WHERE imovel.id = :idImovel "
    )
    fun obterSistemaAbastecimentoImovel(idImovel: Int): Int?

    @Query(
        value = " SELECT new br.gov.pa.cosanpa.api.dto.Dto( " +
                " cg.id as id, " +
                " cg.descricao as descricao) " +
                " FROM Categoria cg " +
                " Where cg.indicadorUso = ${ConstantesSistema.INDICADOR_USO_ATIVO}" +
                " ORDER BY cg.id "
    )
    fun obterColecaoIdDescricaoCategoriasEmUso() : List<Dto>

    @Query(
        value = " SELECT DISTINCT ct.id " +
                " FROM Imovel imovel " +
                " INNER JOIN imovel.consumoTarifa ct "
    )
    fun obterColecaoIdConsumoTarifaEmUso(): List<Int>
}
