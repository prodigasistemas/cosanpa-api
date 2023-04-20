package br.gov.pa.cosanpa.api.dto.arrecadacao.debitoautomatico

import java.time.LocalDate

data class DebitoAutomaticoDTO(
    val id: Int? = null,
    val identificacaoClienteBanco: String? = null,
    val dataExclusao: LocalDate? = null,
    val idImovel: Int? = null,
    val idAgencia: Int? = null
)
