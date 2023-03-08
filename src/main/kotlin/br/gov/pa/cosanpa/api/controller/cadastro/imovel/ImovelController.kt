package br.gov.pa.cosanpa.api.controller.cadastro.imovel

import br.gov.pa.cosanpa.api.service.cadastro.endereco.EnderecoService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/imoveis")
class ImovelController(
    private val service: EnderecoService
) {

    @GetMapping("/{id}")
    fun buscarImoveisPorRotaId(@PathVariable id: Int): String = service.pesquisarEnderecoFormatadoImovel(id)

}