package br.gov.pa.cosanpa.api.dto.micromedicao.medicao

import java.time.LocalDate

data class MedicaoHistoricoDTO(
    val id : Int? = null,
    val dataLeituraAnteriorFaturamento: LocalDate? = null,
    val idImovel: Int? = null,
    val idLigacaoAgua: Int? = null,
    val idMedicaoTipo: Int? = null
)
