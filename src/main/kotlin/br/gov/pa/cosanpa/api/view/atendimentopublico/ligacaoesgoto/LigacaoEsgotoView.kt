package br.gov.pa.cosanpa.api.view.atendimentopublico.ligacaoesgoto

import java.math.BigDecimal
import java.time.LocalDate


data class LigacaoEsgotoView(
    val id: Int,
    val numeroConsumoMinimoEsgoto: Int,
    val percentualAguaConsumidaColetada: BigDecimal,
    val dataLigacao: String,
)
