package br.gov.pa.cosanpa.api.controller.leiturista

import br.gov.pa.cosanpa.api.service.leiturista.LeituristaService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/leituristas")
class LeituristaController(
    private val service: LeituristaService
) {

    @GetMapping("/usuario/{id}")
    fun buscarPorUsuario(@PathVariable id: Int): ResponseEntity<*> = service.buscarPorUsuario(id)
}