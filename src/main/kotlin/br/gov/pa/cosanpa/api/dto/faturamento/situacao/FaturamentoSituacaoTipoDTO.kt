package br.gov.pa.cosanpa.api.dto.faturamento.situacao

data class FaturamentoSituacaoTipoDTO(
    val id: Int? = null,
    val descricao: String? = null,
    val indicadorValidoAgua: Int? = null,
    val indicadorValidoEsgoto: Int? = null,
    val indicadorParalisacaoFaturamento: Int? = null,
    val indicadorParalisacaoLeitura: Int? = null,
    val idLeituraAnormalidadeConsumoComLeitura: Int? = null,
    val idLeituraAnormalidadeConsumoSemLeitura: Int? = null,
    val idLeituraAnormalidadeLeituraComLeitura: Int? = null,
    val idLeituraAnormalidadeLeituraSemLeitura: Int? = null,
)
