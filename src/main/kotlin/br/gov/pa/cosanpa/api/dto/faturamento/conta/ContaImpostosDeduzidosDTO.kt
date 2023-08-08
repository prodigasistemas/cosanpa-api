package br.gov.pa.cosanpa.api.dto.faturamento.conta

import java.math.BigDecimal

data class ContaImpostosDeduzidosDTO(
    val id: Int? = null,
    val valorBaseCalculo: BigDecimal? = null,
    val valorImposto: BigDecimal? = null,
    val percentualAliquota: BigDecimal? = null,
    val idConta: Int? = null,
    val idImpostoTipo: Int? = null,
)
