package br.gov.pa.cosanpa.api.service.consumo

import br.gov.pa.cosanpa.api.repository.consumo.ConsumoHistoricoRepository
import br.gov.pa.cosanpa.api.view.consumo.ConsumoHistoricoView
import org.springframework.stereotype.Service

@Service
class ConsumoHistoricoService(
    private val repository: ConsumoHistoricoRepository
) {

    fun obterVolumeMedioAguaOuEsgoto(
        idImovel: Int,
        idLigacao: Int,
        amReferenciaInicial: Int,
        amReferenciaFinal: Int
    ): List<ConsumoHistoricoView>? = repository.obterVolumeMedioAguaEsgoto(idImovel, idLigacao, amReferenciaInicial, amReferenciaFinal)
}
