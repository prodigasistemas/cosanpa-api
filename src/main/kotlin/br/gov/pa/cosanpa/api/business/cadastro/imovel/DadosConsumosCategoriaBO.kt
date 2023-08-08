package br.gov.pa.cosanpa.api.business.cadastro.imovel

import br.gov.pa.cosanpa.api.dto.cadastro.imovel.CategoriaDTO

class DadosConsumosCategoriaBO (
    private val categoria: CategoriaDTO
) {
    fun obterReferenciaAltoConsumo(): Int? = multiplicarQuantidadeEconomias(categoria.consumoAlto)
    fun obterReferenciaBaixoConsumo(): Int? =  multiplicarQuantidadeEconomias(categoria.mediaBaixoConsumo)
    fun obterReferenciaEstouroConsumo(): Int? = multiplicarQuantidadeEconomias(categoria.consumoEstouro)
    fun obterMaximoEstouroConsumo(): Int? = multiplicarQuantidadeEconomias(categoria.numeroConsumoMaximoEc)

    private fun multiplicarQuantidadeEconomias(multiplicando: Int?): Int? {
        val economias = categoria.quantidadeEconomias?.let {
            if (multiplicando != null) it.toInt() * multiplicando
            else 0
        }
        return economias
    }

}

