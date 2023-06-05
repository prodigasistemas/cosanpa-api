package br.gov.pa.cosanpa.api.dto.cobranca

import java.math.BigDecimal
import java.time.LocalDateTime

data class CobrancaDocumentoDTO(
    val id: Int? = null,
    val valorDocumento: BigDecimal? = null,
    val numeroSequencialDocumento: Int? = null,
    val emissao: LocalDateTime? = null ,
    val idLocalidade: Int? = null,
    val idImovel: Int? = null,
    val idDocumentoTipo: Int? = null,
)
