package br.gov.pa.cosanpa.api.dto.cadastro.cliente

data class ClienteDTO(
    val id: Int? = null,
    val nome: String? = null,
    val cpf: String? = null,
    val cnpj: String? = null
)
