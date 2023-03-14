package br.gov.pa.cosanpa.api.business.cadastro.imovel

import br.gov.pa.cosanpa.api.dto.cadastro.imovel.CategoriaDTO

class ObterDadosConsumosCategoria (
    private val categoria: CategoriaDTO
) {
    fun obterReferenciaAltoConsumo(): Int = categoria.consumoAlto!! * obterQuantidadeEconomias()
    fun obterReferenciaBaixoConsumo(): Int = categoria.mediaBaixoConsumo!! + obterQuantidadeEconomias()
    fun obterReferenciaEstouroConsumo(): Int = categoria.consumoEstouro!! * obterQuantidadeEconomias()
    fun obterMaximoEstouroConsumo(): Int = categoria.numeroConsumoMaximoEc!! * obterQuantidadeEconomias()

    private fun obterQuantidadeEconomias() = categoria.quantidadeEconomias!!.toInt()

}

