package br.gov.pa.cosanpa.api.service.micromedicao.leiturista

import br.gov.pa.cosanpa.api.repository.micromedicao.leiturista.LeituristaRepository
import br.gov.pa.cosanpa.api.view.leiturista.LeituristaView
import br.gov.pa.cosanpa.api.view.leiturista.LeituristaViewMapper
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class LeituristaService(
    private val repository: LeituristaRepository,
    private val viewMapper: LeituristaViewMapper
) {

    fun buscarPorUsuario(usuarioId: Int): ResponseEntity<List<LeituristaView>> {
        val leituristas = repository.findAllByUsuarioId(usuarioId)
        val leituristaViewList = leituristas.map { leiturista ->
            viewMapper.map(leiturista)
        }
        return if (leituristas.isEmpty()) {
            ResponseEntity.noContent().build()
        } else {

            ResponseEntity.ok().body(leituristaViewList)
        }
    }
}