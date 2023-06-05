package br.gov.pa.cosanpa.api.dto

data class Dto(
    override val id: Int? = null,
    override val descricao: String? = null,
    override val nome: String? = null,
    override val codigo: String? = null
) : IDto
