package br.gov.pa.cosanpa.api.dto.cadastro.cliente

import java.time.LocalDate

data class ClienteImovelContaDTO(
    val id: Int? = null,
    val indicadorNomeConta: Int? = null,
    val dataInicioRelacao: LocalDate? = null,
    val dataFimRelacao: LocalDate? = null,
    val idCliente: Int? = null,
    val idImovel: Int? = null,
    val idConta: Int? = null,
    val idClienteRelacaoTipo: Int? = null
)