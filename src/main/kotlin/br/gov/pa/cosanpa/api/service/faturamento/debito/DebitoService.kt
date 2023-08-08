package br.gov.pa.cosanpa.api.service.faturamento.debito

import br.gov.pa.cosanpa.api.dto.faturamento.debitocredito.DebitoCobradoDTO
import br.gov.pa.cosanpa.api.repository.faturamento.debito.DebitoRepository
import br.gov.pa.cosanpa.api.view.faturamento.debitocredito.DebitoCreditoViewMapper
import org.springframework.stereotype.Service

@Service
class DebitoService(
    private val repository: DebitoRepository,
    private val viewMapper: DebitoCreditoViewMapper
) {

    fun obterColecaoDebitosCobradosParcelamento(idConta: Int) =
        repository.obterColecaoDebitoCobradoDeParcelamento(idConta)

    fun obterColecaoDebitosCobradosNaoParcelamento(idConta: Int) = repository.obterDebitoCobradoNaoParcelamento(idConta)

    fun obterDebitosCobradoView(dto: DebitoCobradoDTO) = viewMapper.mapDebitoCobrado(dto)

    fun obterDadosDebitoTipo(idDebitoTipo: Int) = repository.obterDadosDebitoTipo(idDebitoTipo)

    fun obterDebitoTipoView(idDebitoTipo: Int) = viewMapper.mapDebitoCreditoTipo(obterDadosDebitoTipo(idDebitoTipo))

    fun obterFinanciamentoTipoView(idFinanciamentoTipo: Int) = viewMapper.map(repository.obterDadosFinanciamentoTipo(idFinanciamentoTipo))
}