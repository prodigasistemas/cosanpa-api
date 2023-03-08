package br.gov.pa.cosanpa.api.controller.cadastro.cliente

import br.gov.pa.cosanpa.api.service.cadastro.endereco.EnderecoService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/clientes")
class ClienteController(
    private val enderecoService: EnderecoService
) {

    @GetMapping("/{id}")
    fun pesquisarEnderecoCorrespondencia(@PathVariable id: Int): String = enderecoService.pesquisarEnderecoCorrespondencia(id)
}