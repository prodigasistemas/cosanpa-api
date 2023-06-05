package br.gov.pa.cosanpa.api.dto.micromedicao.consumo

data class ConsumoHistoricoDTO(
    val id: Int? = null,
    val referencia: Int? = null,
    val numeroCalculoConsumoMedia: Int? = null,
    val numeroConsumoFaturadoMes: Int? = null,
    val idImovel: Int? = null,
    val idConsumoTipo: Int? = null,
    val idConsumoAnormalidade: Int? = null,
    val idLigacaoTipo: Int? = null
)
