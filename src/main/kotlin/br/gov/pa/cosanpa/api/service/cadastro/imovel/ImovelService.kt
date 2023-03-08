package br.gov.pa.cosanpa.api.service.cadastro.imovel

import br.gov.pa.cosanpa.api.repository.cadastro.imovel.ImovelRepository
import br.gov.pa.cosanpa.api.dto.cadastro.imovel.ImovelDTO
import org.springframework.stereotype.Service

@Service
class ImovelService(
    private val repository: ImovelRepository
) {
    fun obterConsumoTarifaImovel(idImovel: Int) : ImovelDTO = repository.obterConsumoTarifa(idImovel)
    
    fun obterCategorias(idImovel: Int) = repository.obterCategoriasPorImovel(idImovel)
}
