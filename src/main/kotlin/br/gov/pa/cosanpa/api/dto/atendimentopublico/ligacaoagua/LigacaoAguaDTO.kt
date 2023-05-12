package br.gov.pa.cosanpa.api.dto.atendimentopublico.ligacaoagua

import java.time.LocalDate

data class LigacaoAguaDTO(
    val id: Int? = null,
    val numeroConsumoMinimoAgua: Int? = null,
    val dataLigacao: LocalDate? = null,
    val dataCorte: LocalDate? = null,
    val numeroLacre: String? = null,
    val idHidrometroInstalacaoHistorico: Int? = null
)
