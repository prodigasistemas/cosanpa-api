package br.gov.pa.cosanpa.api.dto.micromedicao.consumo

data class ConsumoHistoricoDTO(
    val id: Int? = null,
    val referencia: Int? = null,
    val numeroCalculoConsumoMedia: Int? = null,
    val imovel: Int? = null,
    val consumoTipo: Int? = null,
    val consumoAnormalidade: Int? = null,
    val ligacaoTipo: Int? = null
)
