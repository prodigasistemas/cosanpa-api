package br.gov.pa.cosanpa.api.dto.cobranca

import java.math.BigDecimal
import java.time.LocalDate

data class CobrancaDocumentoItemDTO(
    val id: Int? = null,
    val valorItemCobrado: BigDecimal? = null,
    val dataSituacaoDebito: LocalDate? = null,
    val valorAcrescimos: BigDecimal? = null,
    val numeroParcelasAntecipadas: Int? = null,
    val idCobrancaDocumento: Int? = null,
    val idDocumentoTipo: Int? = null,
    val idConta: Int? = null,
    val referenciaConta: Int? = null,
    val dataVencimentoConta: LocalDate? = null
)
