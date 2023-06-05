package br.gov.pa.cosanpa.api.service.cobranca

import br.gov.pa.cosanpa.api.dominio.cobranca.CobrancaDebitoSituacao
import br.gov.pa.cosanpa.api.dominio.cobranca.DocumentoTipo
import br.gov.pa.cosanpa.api.dto.cobranca.CobrancaDocumentoItemDTO
import br.gov.pa.cosanpa.api.repository.cobranca.CobrancaDocumentoRepository
import br.gov.pa.cosanpa.api.view.cobranca.CobrancaDocumentoViewMapper
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class CobrancaDocumentoService(
    private val repository: CobrancaDocumentoRepository,
    private val viewMapper: CobrancaDocumentoViewMapper
) {

    fun obterDadosCobrancaDocumentoAvisoCorte(idImovel: Int, dataEmissao: LocalDateTime) = repository.obterCobrancaDocumentoImpressaoSimultanea(
        idImovel = idImovel,
        dataEmissao = dataEmissao,
        idCobrancaDebitoSituacao = CobrancaDebitoSituacao.PENDENTE,
        idDocumentoTipo = DocumentoTipo.AVISO_CORTE
    )

    fun obterColecaoCobrancaDocumentoItem(idCobrancaDocumento: Int) = repository.obterCobrancaDocumentoItemReferenteConta(idCobrancaDocumento)

    fun obterCobrancaDocumentoItemView(dto: CobrancaDocumentoItemDTO) = viewMapper.mapItem(dto)

    fun obterDocumentoTipoView(idDocumentoTipo: Int) = viewMapper.map(repository.obterDocumentoTipo(idDocumentoTipo))


}