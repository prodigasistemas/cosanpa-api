package br.gov.pa.cosanpa.api.controller.rota

import br.gov.pa.cosanpa.api.service.rota.RotaService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/rotas")
class RotaController(
    private val service: RotaService
) {

    @GetMapping("/leiturista/usuario/{id}")
    fun buscarRotasPorUsuario(@PathVariable id: Int) : ResponseEntity<*> = service.buscarRotasPorUsuario(id)
}