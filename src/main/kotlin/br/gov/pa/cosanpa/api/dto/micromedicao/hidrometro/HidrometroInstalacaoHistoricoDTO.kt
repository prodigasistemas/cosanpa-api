package br.gov.pa.cosanpa.api.dto.micromedicao.hidrometro

import java.time.LocalDate

data class HidrometroInstalacaoHistoricoDTO(
    val id: Int? = null,
    val dataInstalacao: LocalDate? = null,
    val numeroLeituraInstalacao: Int? = null,
    val dataRetirada: LocalDate? = null,
    val idHidrometro: Int? = null,
    val idHidrometroProtecao: Int? = null,
    val idHidrometroLocalInstalacao: Int? = null,
    val idRateioTipo: Int? = null,
    val idImovel: Int? = null,
    val idLigacaoAgua: Int? = null,
    val idMedicaoTipo: Int? = null
)