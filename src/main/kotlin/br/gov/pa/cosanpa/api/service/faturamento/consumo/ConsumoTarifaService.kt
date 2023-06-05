package br.gov.pa.cosanpa.api.service.faturamento.consumo

import br.gov.pa.cosanpa.api.dto.faturamento.consumo.ConsumoTarifaCategoriaDTO
import br.gov.pa.cosanpa.api.repository.faturamento.consumo.ConsumoTarifaRepository
import br.gov.pa.cosanpa.api.view.faturamento.consumo.ConsumoTarifaCategoriaView
import br.gov.pa.cosanpa.api.view.faturamento.consumo.ConsumoTarifaFaixaView
import br.gov.pa.cosanpa.api.view.faturamento.consumo.ConsumoTarifaViewMapper
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class ConsumoTarifaService(
    private val repository: ConsumoTarifaRepository,
    private val viewMapper: ConsumoTarifaViewMapper
) {

    fun obterNumeroConsumoMinimoTarifaCategoria(idConsumoTarifaVigencia: Int, idCategoria: Int): Int {
        return repository.obterConsumoMinimoTarifaCategoria(idConsumoTarifaVigencia, idCategoria)
    }

    fun obterDataTarifaVigenciaCorrente(idConsumoTarifa: Int) =
        repository.obterDataConsumoTarifaVigente(idConsumoTarifa, LocalDate.now())

    fun obterDadosConsumoTarifa(idConsumoTarifa: Int) = repository.obterDadosConsumoTarifa(idConsumoTarifa)

    fun obterDadosConsumoTarifaView(idConsumoTarifa: Int) = viewMapper.map(obterDadosConsumoTarifa(idConsumoTarifa))

    fun obterDadosConsumoTarifaCategoriaProporcional(
        dataLeituraAnterior: LocalDate,
        idCategoria: Int,
        idConsumoTarifa: Int
    ) = repository.obterDadosConsumoTarifaProporcional(
        dataLeituraAnterior = dataLeituraAnterior,
        dataLeituraAtual = LocalDate.now(),
        idCategoria = idCategoria,
        idConsumoTarifa = idConsumoTarifa
    )

    fun obterDadosConsumoTarifaCategoriaPorDataVingente(
        dataVigencia: LocalDate,
        idConsumoTarifa: Int,
        idCategoria: Int
    ): List<ConsumoTarifaCategoriaDTO> {
        return repository.obterDadosConsumoTarifaCategoriaPorDataVigencia(dataVigencia, idConsumoTarifa, idCategoria)
    }

    fun obterConsumoTarifaCategoriaView(consumoTarifaCategoriaDTO: ConsumoTarifaCategoriaDTO): ConsumoTarifaCategoriaView {
        return viewMapper.mapConsumoTarifaCategoria(consumoTarifaCategoriaDTO)
    }

    fun obterColecaoConsumoTarifaFaixaPorCategoria(idConsumoTarifaCategoria: Int) =
        repository.obterDadosConsumoTarifaFaixaPorCategoria(idConsumoTarifaCategoria)

    fun obterConsumoTarifaFaixaView(idConsumoTarifaCategoria: Int): List<ConsumoTarifaFaixaView> {
        return obterColecaoConsumoTarifaFaixaPorCategoria(idConsumoTarifaCategoria).map {
            viewMapper.mapConsumoTarifaFaixa(it)
        }
    }

    fun obterColecaoTarifaTipoCalculoEmUso() = repository.obterColecaoTarifaTipoCaluloEmUso()

}
