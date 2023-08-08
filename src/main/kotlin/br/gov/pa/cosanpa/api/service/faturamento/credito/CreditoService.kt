package br.gov.pa.cosanpa.api.service.faturamento.credito

import br.gov.pa.cosanpa.api.dto.faturamento.debitocredito.CreditoRealizadoDTO
import br.gov.pa.cosanpa.api.repository.faturamento.credito.CreditoRepository
import br.gov.pa.cosanpa.api.view.faturamento.debitocredito.DebitoCreditoViewMapper
import org.springframework.stereotype.Service

@Service
class CreditoService(
    private val repository: CreditoRepository,
    private val viewMapper: DebitoCreditoViewMapper
) {

    fun obterColecaoCreditosRealizados(idConta: Int) = repository.obterCreditosRealizadosPorContaId(idConta)

    fun obterColecaoCreditosRealizadosView(creditoRealizadoDTO: CreditoRealizadoDTO) = viewMapper.mapCreditoRealizado(creditoRealizadoDTO)

    fun obterCreditoTipoView(idCreditoTipo: Int) = viewMapper.mapDebitoCreditoTipo(repository.obterDadosCreditoTipo(idCreditoTipo))
}
