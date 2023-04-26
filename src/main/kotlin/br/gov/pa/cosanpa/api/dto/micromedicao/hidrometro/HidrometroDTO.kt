package br.gov.pa.cosanpa.api.dto.micromedicao.hidrometro

import java.time.LocalDate

data class HidrometroDTO(
    val numero: String? = null,
    val numeroDigitosLeitura: Short? = null,
    val dataInstalacao: LocalDate? = null,
    val numeroLeituraInstalacao: Int? = null,
    val idImovel: Int? = null,
    val descricaoHidrometroLocalInstalacao: String? = null,
    val idRateioTipo: Int? = null
)
