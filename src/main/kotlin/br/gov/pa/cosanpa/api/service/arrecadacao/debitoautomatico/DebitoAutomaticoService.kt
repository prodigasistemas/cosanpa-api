package br.gov.pa.cosanpa.api.service.arrecadacao.debitoautomatico

import br.gov.pa.cosanpa.api.repository.arrecadacao.debitoautomatico.DebitoAutomaticoRepository
import org.springframework.stereotype.Service

@Service
class DebitoAutomaticoService(
    private val repository: DebitoAutomaticoRepository
) {

    fun obterDadosDebitoAutomaticoPorImovelId(idImovel: Int) = repository.obterDadosDebitoAutomaticoPorImovelId(idImovel)

}