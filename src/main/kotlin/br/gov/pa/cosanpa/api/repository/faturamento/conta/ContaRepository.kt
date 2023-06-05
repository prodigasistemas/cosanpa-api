package br.gov.pa.cosanpa.api.repository.faturamento.conta

import br.gov.pa.cosanpa.api.dominio.faturamento.debito.DebitoCreditoSituacao
import br.gov.pa.cosanpa.api.dominio.faturamento.conta.Conta
import br.gov.pa.cosanpa.api.dto.Dto
import br.gov.pa.cosanpa.api.dto.cadastro.cliente.ClienteImovelContaDTO
import br.gov.pa.cosanpa.api.dto.cadastro.imovel.CategoriaDTO
import br.gov.pa.cosanpa.api.dto.faturamento.conta.ContaDTO
import br.gov.pa.cosanpa.api.dto.faturamento.conta.ContaImpostosDeduzidosDTO
import br.gov.pa.cosanpa.api.dto.faturamento.conta.ContaMensagemDTO
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface ContaRepository: JpaRepository<Conta, Int> {

    @Query(
        value = "SELECT new br.gov.pa.cosanpa.api.dto.faturamento.conta.ContaDTO(" +
                " conta.id as id, " +
                " conta.referencia as referencia, " +
                " conta.dataVencimento as dataVencimento, " +
                " conta.dataValidade as dataValidade, " +
                " conta.lote as lote, " +
                " conta.sublote as sublote, " +
                " conta.digitoVerificador as digitoVerificador, " +
                " conta.percentualEsgoto as percentualEsgoto, " +
                " conta.codigoSetorComercial as codigoSetorComercial, " +
                " conta.quadra as quadra, " +
                " imovel.id as idImovel, " +
                " ligacaoAguaSituacao.id as idLigacaoAguaSituacao, " +
                " ligacaoEsgotoSituacao.id as idLigacaoEsgotoSituacao, " +
                " imovelPerfil.id as idImovelPerfil, " +
                " consumoTarifa.id as idConsumoTarifa," +
                " fg.id as idFaturamentoGrupo, " +
                " localidade.id as idLocalidade) " +
                " FROM Conta conta " +
                " INNER JOIN conta.imovel imovel " +
                " INNER JOIN conta.debitoCreditoSituacaoAtual debitoCreditoSituacaoAtual " +
                " INNER JOIN conta.localidade localidade " +
                " INNER JOIN localidade.gerenciaRegional gerenciaRegional " +
                " INNER JOIN conta.ligacaoAguaSituacao ligacaoAguaSituacao " +
                " INNER JOIN conta.ligacaoEsgotoSituacao ligacaoEsgotoSituacao " +
                " INNER JOIN conta.imovelPerfil imovelPerfil " +
                " INNER JOIN conta.consumoTarifa consumoTarifa " +
                " LEFT JOIN conta.faturamentoGrupo fg " +
                " WHERE imovel.id = :idImovel AND " +
                "  conta.referencia = :anoMesReferencia " +
                " AND debitoCreditoSituacaoAtual.id = ${DebitoCreditoSituacao.PRE_FATURADA} " +
                " AND NOT EXISTS ( from MovimentoContaPrefaturada mcpf where mcpf.anoMesReferenciaPreFaturamento = fg.referencia and imovel.id = mcpf.imovel.id  )"
    )
    fun obterContaPreFaturada(idImovel: Int, anoMesReferencia: Int) : ContaDTO?
    
    @Query(
        value = " SELECT new br.gov.pa.cosanpa.api.dto.cadastro.cliente.ClienteImovelContaDTO( " +
                " clienteConta.id as id, " +
                " clienteConta.indicadorNomeConta as indicadorNomeConta, " +
                " cliente.id as idCliente, " +
                " conta.id as idConta, " +
                " clienteConta.clienteRelacaoTipo.id as idClienteRelacaoTipo) " +
                " FROM Conta conta " +
                " INNER JOIN conta.clienteContas clienteConta " +
                " INNER JOIN clienteConta.cliente cliente " +
                " WHERE conta.id = :idConta " +
                " AND conta.referencia = :anoMesReferencia "
    )
    fun obterColecaoClienteContas(idConta: Int, anoMesReferencia: Int) : List<ClienteImovelContaDTO>

    @Query(
        value = " SELECT new br.gov.pa.cosanpa.api.dto.cadastro.imovel.CategoriaDTO " +
                " (catg.id as id, " +
                "  catg.descricao as descricao, " +
                "  SUM(cc.quantidadeEconomia) as quantidadeEconomias," +
                "  catg.consumoAlto as consumoAlto," +
                "  catg.consumoEstouro as consumoEstouro," +
                "  catg.numeroConsumoMaximoEc as numeroConsumoMaximoEc," +
                "  catg.mediaBaixoConsumo as mediaBaixoConsumo," +
                "  catg.vezesMediaAltoConsumo as vezesMediaAltoConsumo," +
                "  catg.vezesMediaEstouro as vezesMediaEstouro," +
                "  catg.porcentagemMediaBaixoConsumo as porcentagemMediaBaixoConsumo) " +
                " FROM ContaCategoria cc " +
                " INNER JOIN cc.conta c  " +
                " INNER JOIN cc.categoria catg  " +
                " WHERE c.id = :idConta " +
                " GROUP BY catg.id, catg.descricao, catg.consumoEstouro, catg.vezesMediaEstouro," +
                " c.id, catg.consumoAlto, catg.mediaBaixoConsumo, catg.vezesMediaAltoConsumo," +
                " catg.porcentagemMediaBaixoConsumo,catg.numeroConsumoMaximoEc," +
                " catg.fatorEconomias, catg.categoriaTipo.id, catg.categoriaTipo.descricao " +
                " ORDER BY catg.descricao "
    )
    fun obterColecaoDadosCategoriasPorContaId(idConta: Int): List<CategoriaDTO>

    @Query(
        value = " SELECT COUNT(comunicado.id) FROM ComunicadoEmitirConta comunicado " +
                " INNER JOIN comunicado.imovel imovel " +
                " WHERE imovel.id = :idImovel " +
                " AND comunicado.tipoComunicado = :tipoComunicado " +
                " AND comunicado.indicadorEmitido = :indicadorEmitido " +
                " AND comunicado.referencia = :referencia "
    )
    fun quantidadeComunicadoEmitido(idImovel: Int, indicadorEmitido: Int, tipoComunicado: Int, referencia: Int): Number

    @Query(
        value = "SELECT new br.gov.pa.cosanpa.api.dto.faturamento.conta.ContaMensagemDTO( " +
                " contaMensagem.descricaoContaMensagem01 as descricaoContaMensagem01, " +
                " contaMensagem.descricaoContaMensagem02 as descricaoContaMensagem02, " +
                " contaMensagem.descricaoContaMensagem03 as descricaoContaMensagem03) " +
                " FROM ContaMensagem contaMensagem  " +
                " LEFT JOIN contaMensagem.gerenciaRegional gerenciaRegional  " +
                " LEFT JOIN contaMensagem.localidade localidade  " +
                " LEFT JOIN contaMensagem.setorComercial setorComercial  " +
                " LEFT JOIN contaMensagem.faturamentoGrupo faturamentoGrupo  " +
                " LEFT JOIN contaMensagem.quadra quadra  " +
                " WHERE contaMensagem.anoMesReferenciaFaturamento = :referencia  " +
                " AND (faturamentoGrupo.id = :idFaturamentoGrupo OR faturamentoGrupo.id IS NULL) " +
                " AND (gerenciaRegional.id = :idGerenciaRegional OR gerenciaRegional.id IS NULL)" +
                " AND (localidade.id = :idLocalidade OR localidade.id IS NULL) " +
                " AND (setorComercial.id = :idSetorComercial OR setorComercial.id IS NULL)" +
                " AND (quadra.id = :idQuadra OR quadra.id IS NULL)"
    )
    fun obterContaMensagem3Partes(referencia: Int,
                                  idFaturamentoGrupo: Int?,
                                  idGerenciaRegional: Int?,
                                  idLocalidade: Int?,
                                  idSetorComercial: Int?,
                                  idQuadra: Int?) : ContaMensagemDTO?

    @Query(
        value = " SELECT new br.gov.pa.cosanpa.api.dto.faturamento.conta.ContaImpostosDeduzidosDTO( " +
                " cid.id as id, " +
                " cid.valorBaseCalculo as valorBaseCalculo, " +
                " cid.valorImposto as valorImposto, " +
                " cid.percentualAliquota as percentualAliquota, " +
                " cid.conta.id as idConta, " +
                " cid.impostoTipo.id as idImpostoTipo)" +
                " FROM ContaImpostosDeduzidos cid " +
                " INNER JOIN cid.conta conta " +
                " INNER JOIN cid.impostoTipo impostoTipo " +
                " WHERE conta.id = :idConta "
    )
    fun obterDadosContaImpostosDeduzidos(idConta: Int) : List<ContaImpostosDeduzidosDTO>

    @Query(
        value = " SELECT new br.gov.pa.cosanpa.api.dto.Dto( " +
                " it.id as id, " +
                " it.descricao as descricao) " +
                " FROM ImpostoTipo it " +
                " WHERE it.id = :idImpostoTipo"
    )
    fun obterDadosImpostoTipo(idImpostoTipo: Int) : Dto
}