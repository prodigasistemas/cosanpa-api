package br.gov.pa.cosanpa.api.controller.imovel

import br.gov.pa.cosanpa.api.service.imovel.ImovelService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/imoveis")
class ImovelController(
    private val service: ImovelService
) {

    @GetMapping("/{id}")
    fun buscarImoveisPorRotaId(@PathVariable id: Int): String {return service.pesquisarEnderecoFormatado(id)}

}