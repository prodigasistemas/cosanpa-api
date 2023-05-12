package br.gov.pa.cosanpa.api.dto.atendimentopublico.ligacaoesgoto

import java.math.BigDecimal
import java.time.LocalDate

data class LigacaoEsgotoDTO(
    val id: Int? = null,
    val numeroConsumoMinimoEsgoto: Int? = null,
    val percentualAguaConsumidaColetada: BigDecimal? = null,
    val dataLigacao: LocalDate? = null,
)
