package br.gov.pa.cosanpa.api.repository.cobranca

import br.gov.pa.cosanpa.api.dominio.cobranca.CobrancaDocumento
import br.gov.pa.cosanpa.api.dto.cobranca.CobrancaDocumentoDTO
import br.gov.pa.cosanpa.api.dto.cobranca.CobrancaDocumentoItemDTO
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.sql.Timestamp

interface CobrancaDocumentoRepository : JpaRepository<CobrancaDocumento, Int> {
    
    @Query(
        value = "SELECT new br.gov.pa.cosanpa.api.dto.cobranca.CobrancaDocumentoDTO(" +
                " cbdo.id as id, " +
                " cbdo.valorDocumento as valorDocumento, " +
                " cbdo.numeroSequencialDocumento as numeroSequencialDocumento, " +
                " cbdo.emissao as emissao, " +
                " loca.id as idlocalidade, " +
                " cbdo.imovel.id as idImovel, " +
                " cbdo.documentoTipo.id as idDocumentoTipo) " +
                " FROM CobrancaDocumento as cbdo " +
                " LEFT JOIN cbdo.localidade as loca " +
                " WHERE cbdo.imovel.id = :idImovel " +
                " AND cbdo.documentoTipo.id = :idDocumentoTipo " +
                " AND cbdo.cobrancaDebitoSituacao.id = :idCobrancaDebitoSituacao " +
                " AND cbdo.emissao >= :dataEmissao " +
                " ORDER BY cbdo.emissao DESC" +
                " LIMIT 1 "
    )
    fun obterCobrancaDocumentoImpressaoSimultanea(
        idImovel: Int,
        idDocumentoTipo: Int,
        idCobrancaDebitoSituacao: Int,
        dataEmissao: Timestamp
    ): CobrancaDocumentoDTO?

    @Query(
        value = " SELECT new br.gov.pa.cosanpa.api.dto.cobranca.CobrancaDocumentoItemDTO(" +
                " cdit.id as id, " +
                " cdit.valorItemCobrado as valorItemCobrado, " +
                " cdit.dataSituacaoDebito as dataSituacaoDebito, " +
                " cdit.valorAcrescimos as valorAcrescimos, " +
                " cdit.numeroParcelasAntecipadas as numeroParcelasAntecipadas, " +
                " cdit.cobrancaDocumento.id as idCobrancaDocumento, " +
                " cdit.documentoTipo.id as idDocumentoTipo, " +
                " cnta.id as idConta, " +
                " cnta.referencia as referenciaConta, " +
                " cnta.dataVencimento as dataVencimentoConta) " +
                " FROM CobrancaDocumentoItem  cdit " +
                " LEFT JOIN cdit.conta cnta " +
                " LEFT JOIN cnta.debitoCreditoSituacaoAtual " +
                " WHERE cdit.cobrancaDocumento.id = :idCobrancaDocumento " +
                " AND cnta.id IS NOT NULL " +
                " ORDER BY cdit.conta.referencia "
    )
    fun obterCobrancaDocumentoItemReferenteConta(idCobrancaDocumento: Int) : List<CobrancaDocumentoItemDTO>
}