package br.gov.pa.cosanpa.api.service.leiturista

import br.gov.pa.cosanpa.api.view.leiturista.LeituristaView
import br.gov.pa.cosanpa.api.view.leiturista.LeituristaViewMapper
import br.gov.pa.cosanpa.api.repository.leiturista.LeituristaRepository
import org.springframework.stereotype.Service

@Service
class LeituristaService (
    private val repository: LeituristaRepository,
    private val viewMapper: LeituristaViewMapper
) {

    fun buscarLeituristaPorUsuarioId(usuarioId: Int): List<LeituristaView> {
        val leituristas = repository.findAllByUsuarioId(usuarioId)
        return leituristas.map {leiturista ->
            viewMapper.map(leiturista)
        }
    }
}