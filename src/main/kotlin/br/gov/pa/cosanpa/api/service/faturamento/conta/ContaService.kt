package br.gov.pa.cosanpa.api.service.faturamento.conta

import br.gov.pa.cosanpa.api.dto.faturamento.conta.ContaDTO
import br.gov.pa.cosanpa.api.repository.faturamento.conta.ContaRepository
import org.springframework.stereotype.Service

@Service
class ContaService(
    private val repository: ContaRepository
) {

    fun obterContaPreFaturadaPorImovelId(idImovel: Int, anoMesReferencia: Int): ContaDTO? {
        return repository.obterContaPreFaturada(idImovel, anoMesReferencia)
    }
}