package br.gov.pa.cosanpa.api.controller.faturamento.`is`

import br.gov.pa.cosanpa.api.service.faturamento.impressaosimultanea.FaturamentoISService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/gerardados")
class FaturamentoISController(
    private val service: FaturamentoISService
) {

    @GetMapping("/imovel/{id}")
    fun obterDadosLeituraImovelUnico(@PathVariable id: Int) = service.obterDadosParaLeituraImovelUnico(id)

    @GetMapping("/rota/{id}")
    fun obterDadosLeituraImoveisPorRota(@PathVariable id: Int) = service.obterDadosParaLeituraPorRota(id)

    @GetMapping("/consumoTarifa/grupo/{id}")
    fun obterDadosLeituraConsumoTarifa(@PathVariable id: Int) = service.obterDadosConsumoTarifaPorGrupo(id)
}