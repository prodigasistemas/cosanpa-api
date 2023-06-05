package br.gov.pa.cosanpa.api.dto.arrecadacao.banco

import br.gov.pa.cosanpa.api.dto.IDto

data class AgenciaDTO(
    override val id: Int? = null,
    override val codigo: String? = null,
    val idBanco: Int? = null
) : IDto

