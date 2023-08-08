package br.gov.pa.cosanpa.api.dto

interface IDto {
    val id: Int?
        get() = null
    val descricao: String?
        get() = null
    val nome: String?
        get() = null
    val codigo: String?
        get() = null
}