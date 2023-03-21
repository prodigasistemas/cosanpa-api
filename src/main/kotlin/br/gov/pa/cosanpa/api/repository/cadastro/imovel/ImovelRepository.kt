package br.gov.pa.cosanpa.api.repository.cadastro.imovel

import br.gov.pa.cosanpa.api.dominio.cadastro.imovel.Imovel
import br.gov.pa.cosanpa.api.dto.cadastro.endereco.EnderecoDTO
import br.gov.pa.cosanpa.api.dto.cadastro.imovel.CategoriaDTO
import br.gov.pa.cosanpa.api.dto.cadastro.imovel.ImovelDTO
import br.gov.pa.cosanpa.api.dto.cadastro.imovel.InscricaoDTO
import br.gov.pa.cosanpa.api.dto.cadastro.imovel.SubcategoriaDTO
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
    fun obterDadosSubcategoriasPorImovel(idImovel: Int): List<SubcategoriaDTO>

//    @Query(
//        value = "SELECT gerenciaRegional.nome, " +
//                " localidade.id, " +
//                " localidade.descricao, " +
//                " imovel.nome, " +
//                " clienteUsuario.nome, " +
//                " setorComercial.codigo, " +
//                " quadra.numero, " +
//                " imovel.lote, " +
//                " imovel.sublote, " +
//                " clienteResposanvel.id, " +
//                " clienteResposanvel.nome, " +
//                " ligacaoAguaSituacao.id, " +
//                " ligacaoEsgotoSituacao.id, " +
//                " ligacaoAgua.numeroConsumoMinimoAgua, " +
//                " ligacaoEsgoto.numeroConsumoMinimoEsgoto, " +
//                " ligacaoEsgoto.percentualAguaConsumidaColetada, " +
//                " faturamentoSituacaoTipo.indicadorParalisacaoFaturamento, " +
//                " faturamentoSituacaoTipo.indicadorValidoAgua, " +
//                " faturamentoSituacaoTipo.indicadorValidoEsgoto, " +
//                " imovelCondominio.id, " +
//                " imovel.indicadorImovelCondominio, " +
//                " imovelPerfil.id, " +
//                " consumoTarifa.id, " +
//                " imovel.id, " +
//                " pocoTipo.id, " +
//                " consumoTarifa.tarifaTipoCalculo.id, " +
//                " setorComercial.id, " +
//                " imovel.numeroSequencialRota, " +
//                " gerenciaRegional.id, " +
//                " ligacaoAguaSituacao.indicadorFaturamentoSituacao," +
//                " ligacaoEsgotoSituacao.indicadorFaturamentoSituacao, " +
//                " ice.id, " +
//                " clienteUsuario.cpf, " +
//                " clienteUsuario.cnpj, " +
//                " hidAgua.id, " +
//                " hidPoco.id, " +
//                " ligacaoAguaSituacao.indicadorAbastecimento, " +
//                " ligacaoAgua.id, " +
//                " ligacaoAguaSituacao.descricao, " +
//                " leiturista.id, " +
//                " empresa.id, " +
//                " quadraFace.id, " +
//                " quadraFace.numero, " +
//                " imovel.numeroMorador, " +
//                " medTipoAgua.id, " +
//                " medTipoPoco.id, " +
//                " ligacaoAgua.numeroLacre, " +
//                " logradouroBairro.id, " +
//                " logradouro.id, " +
//                " logradouro.nome, " +
//                " bairro.nome, " +
//                " imovel.numero, " +
//                " imovel.complementoEndereco, " +
//                " hidLocInsAgua.id, " +
//                " hidAgua.dataInstalacao, " +
//                " hidProtAgua.id, " +
//                " hidLocInsPoco.id, " +
//                " hidPoco.dataInstalacao, " +
//                " hidProtPoco.id, " +
//                " usu.login, " +
//                " usu.senha, " +
//                " faturamentoSituacaoTipo.id, " +
//                " imovel.codigoDebitoAutomatico, " +
//                " faturamentoSituacaoTipo.indicadorParalisacaoLeitura, " +
//                " ligacaoAguaSituacao.indicadorConsumoReal, " +
//                " ligacaoAguaSituacao.numeroDiasCorte, " +
//                " ligacaoAgua.dataCorte, " +
//                " imovel.indicadorImovelAreaComum, " +
//                " ligacaoAgua.dataLigacao, " +
//                " ligacaoEsgoto.dataLigacao, " +
//                " clienteImoveisReposanvel.cliente.id, " +
//                " clienteImoveisReposanvel.indicadorNomeConta, " +
//                " clienteImoveisUsuario.cliente.id, " +
//                " clienteImoveisUsuario.indicadorNomeConta, " +
//                " imovel.indicadorEnvioContaFisica, " +
//                " imovel.indicadorParametrosConvenio, " +
//                " quadra.id " +
//                " FROM Imovel imovel " +
//                " INNER JOIN imovel.localidade localidade " +
//                " INNER JOIN localidade.gerenciaRegional gerenciaRegional " +
//                " INNER JOIN imovel.setorComercial setorComercial " +
//                " INNER JOIN imovel.quadra quadra " +
//                " INNER JOIN quadra.rota rota " +
//                " INNER JOIN imovel.ligacaoAguaSituacao ligacaoAguaSituacao " +
//                " INNER JOIN imovel.ligacaoEsgotoSituacao ligacaoEsgotoSituacao " +
//                " INNER JOIN imovel.imovelPerfil imovelPerfil " +
//                " INNER JOIN imovel.consumoTarifa consumoTarifa " +
//                " LEFT JOIN imovel.quadraFace quadraFace " +
//                " LEFT JOIN imovel.ligacaoAgua ligacaoAgua " +
//                " LEFT JOIN ligacaoAgua.hidrometroInstalacaoHistorico hidAgua " +
//                " LEFT JOIN hidAgua.medicaoTipo medTipoAgua " +
//                " LEFT JOIN hidAgua.hidrometroLocalInstalacao hidLocInsAgua " +
//                " LEFT JOIN hidAgua.hidrometroProtecao hidProtAgua " +
//                " LEFT JOIN imovel.hidrometroInstalacaoHistorico hidPoco " +
//                " LEFT JOIN hidPoco.medicaoTipo medTipoPoco " +
//                " LEFT JOIN hidPoco.hidrometroLocalInstalacao hidLocInsPoco " +
//                " LEFT JOIN hidPoco.hidrometroProtecao hidProtPoco " +
//                " LEFT JOIN imovel.ligacaoEsgoto ligacaoEsgoto " +
//                " LEFT JOIN imovel.pocoTipo pocoTipo " +
//                " LEFT JOIN imovel.faturamentoSituacaoTipo faturamentoSituacaoTipo " +
//                " LEFT JOIN imovel.imovelCondominio imovelCondominio " +
//                " LEFT JOIN imovel.clienteImoveis clienteImoveisUsuario WITH " +
//                " (clienteImoveisUsuario.clienteRelacaoTipo.id = :idClienteRelacaoTipoUsuario) AND clienteImoveisUsuario.dataFimRelacao IS NULL " +
//                " LEFT JOIN clienteImoveisUsuario.cliente clienteUsuario " +
//                " LEFT JOIN imovel.clienteImoveis clienteImoveisReposanvel WITH " +
//                " (clienteImoveisReposanvel.clienteRelacaoTipo.id = :idClienteRelacaoTipoResponsavel) AND clienteImoveisReposanvel.dataFimRelacao IS NULL " +
//                " LEFT JOIN clienteImoveisReposanvel.cliente clienteResposanvel " +
//                " LEFT JOIN imovel.imovelContaEnvio ice " +
//                " LEFT JOIN rota.leiturista leiturista " +
//                " LEFT JOIN rota.empresa empresa " +
//                " LEFT JOIN leiturista.usuario usu " +
//                " LEFT JOIN imovel.logradouroBairro logradouroBairro " +
//                " LEFT JOIN logradouroBairro.logradouro logradouro " +
//                " LEFT JOIN logradouroBairro.bairro bairro " +
//                " LEFT JOIN imovel.rotaAlternativa rotaAlternativa " +
//                " WHERE  imovelPerfil.indicadorGerarDadosLeitura = 1 " +
//                " AND imovel.indicadorExclusao <> :icExclusao " +
//                " rota.id = :idRota " +
//                " AND (imovelCondominio.id = :idImovelCondominio OR imovelCondominio.id IS NULL) " +
//                " ORDER BY imovel.indicadorImovelCondominio,localidade.id, setorComercial.codigo,quadra.numero,imovel.lote,imovel.sublote"
//    )
//    fun obterImovelGerarDados(
//        idRota: Int,
//        icExclusao: Int,
//        idImovelCondominio: Int?,
//        idClienteRelacaoTipoUsuario: Int,
//        idClienteRelacaoTipoResponsavel: Int
//    )

    @Query(
        value = "SELECT br.gov.pa.cosanpa.api.dto.cadastro.imovel.ImovelDTO( " +
                " id as id, " +
                " lote as lote, " +
                " sublote as sublote, " +
                " numero as numero, " +
                " nome as nome, " +
                " complementoEndereco as complementoEndereco, " +
                " numeroMorador as numeroMorador, " +
                " idImovelCondominio as idImovelCondominio, " +
                " indicadorImovelCondominio as indicadorImovelCondominio, " +
                " indicadorExclusao as indicadorExclusao, " +
                " numeroSequencialRota as numeroSequencialRota, " +
                " codigoDebitoAutomatico as codigoDebitoAutomatico, " +
                " indicadorImovelAreaComum as indicadorImovelAreaComum, " +
                " indicadorEnvioContaFisica as indicadorEnvioContaFisica, " +
                " indicadorParametrosConvenio as indicadorParametrosConvenio) " +
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
                " LEFT JOIN imovel.quadraFace quadraFace " +
                " LEFT JOIN imovel.ligacaoEsgoto ligacaoEsgoto " +
                " LEFT JOIN imovel.pocoTipo pocoTipo " +
                " LEFT JOIN imovel.faturamentoSituacaoTipo faturamentoSituacaoTipo " +
                " LEFT JOIN imovel.clienteImoveis clienteImoveisUsuario WITH (clienteImoveisUsuario.clienteRelacaoTipo.id = :idClienteRelacaoTipoUsuario) " +
                " AND clienteImoveisUsuario.dataFimRelacao IS NULL " +
                " LEFT JOIN clienteImoveisUsuario.cliente clienteUsuario " +
                " LEFT JOIN imovel.clienteImoveis clienteImoveisReposanvel WITH (clienteImoveisReposanvel.clienteRelacaoTipo.id = :idClienteRelacaoTipoResponsavel) " +
                " AND clienteImoveisReposanvel.dataFimRelacao IS NULL " +
                " LEFT JOIN clienteImoveisReposanvel.cliente clienteResposanvel " +
                " WHERE  imovelPerfil.indicadorGerarDadosLeitura = 1 " +
                " AND imovel.indicadorExclusao <> 1 " +
                " rota.id = :idRota " +
                " AND (imovelCondominio.id = :idImovelCondominio OR imovelCondominio.id IS NULL) " +
                " ORDER BY imovel.indicadorImovelCondominio,localidade.id, setorComercial.codigo,quadra.numero,imovel.lote,imovel.sublote"
    )
    fun obterImovelGerarDados(
        idRota: Int,
        idImovelCondominio: Int?,
        idClienteRelacaoTipoUsuario: Int,
        idClienteRelacaoTipoResponsavel: Int
    ): List<ImovelDTO>
}
