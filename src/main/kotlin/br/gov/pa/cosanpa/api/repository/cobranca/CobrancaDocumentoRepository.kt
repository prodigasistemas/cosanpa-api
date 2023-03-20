package br.gov.pa.cosanpa.api.repository.cobranca

import br.gov.pa.cosanpa.api.dominio.cobranca.CobrancaDocumento
import br.gov.pa.cosanpa.api.dto.cobranca.CobrancaDocumentoDTO
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
                " ORDER BY cbdo.emissao DESC "
    )
    fun obterCobrancaDocumentoImpressaoSimultanea(
        idImovel: Int,
        idDocumentoTipo: Int,
        idCobrancaDebitoSituacao: Int,
        dataEmissao: Timestamp
    ): CobrancaDocumentoDTO?
}