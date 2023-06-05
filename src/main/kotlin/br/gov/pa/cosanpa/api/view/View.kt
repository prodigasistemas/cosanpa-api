package br.gov.pa.cosanpa.api.view

data class View(
    override val id: Int,
    override val descricao: String
) : IView
