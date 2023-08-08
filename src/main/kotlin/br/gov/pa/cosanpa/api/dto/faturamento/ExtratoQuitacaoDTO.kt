package br.gov.pa.cosanpa.api.dto.faturamento

data class ExtratoQuitacaoDTO(
    val id: Int,
    val anoReferencia: Int,
    val indicadorImpressaoNaConta: Int? = null
)
