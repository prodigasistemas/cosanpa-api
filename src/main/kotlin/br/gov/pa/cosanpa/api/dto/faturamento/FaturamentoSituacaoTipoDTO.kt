package br.gov.pa.cosanpa.api.dto.faturamento

data class FaturamentoSituacaoTipoDTO(
    val id: Int? = null,
    val idLeituraAnormalidadeConsumoSemLeitura: Int? = null,
    val idLeituraAnormalidadeConsumoComLeitura: Int? = null,
    val idLeituraAnormalidadeLeituraSemLeitura: Int? = null,
    val idLeituraAnormalidadeLeituraComLeitura: Int? = null,
)
