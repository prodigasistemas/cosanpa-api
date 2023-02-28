package br.gov.pa.cosanpa.api.controller.leiturista

import br.gov.pa.cosanpa.api.view.leiturista.LeituristaView
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

    @GetMapping("/porUsuarioId/{id}")
    fun buscarPorUsuarioId(@PathVariable id: Int): ResponseEntity<*> = service.buscarLeituristaPorUsuarioId(id)
}