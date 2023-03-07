package br.gov.pa.cosanpa.api.service.imovel

import br.gov.pa.cosanpa.api.dominio.cadastro.imovel.Imovel
import br.gov.pa.cosanpa.api.repository.imovel.ImovelRepository
import br.gov.pa.cosanpa.api.view.imovel.ImovelView
import org.springframework.stereotype.Service

@Service
class ImovelService(
    private val repository: ImovelRepository
) {
    fun obterConsumoTarifaImovel(id: Int) : ImovelView = repository.pesquisarConsumoTarifa(id)
}
