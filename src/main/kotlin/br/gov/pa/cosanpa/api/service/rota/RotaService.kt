package br.gov.pa.cosanpa.api.service.rota

import br.gov.pa.cosanpa.api.repository.rota.RotaRepository
import br.gov.pa.cosanpa.api.view.rota.RotaView
import br.gov.pa.cosanpa.api.view.rota.RotaViewMapper
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class RotaService(
    private val repository: RotaRepository,
    private val viewMapper: RotaViewMapper
) {

    fun buscarRotaPorLeituristaId(id: Int): ResponseEntity<List<RotaView>> {
        val rotas = repository.findAllByLeituristaId(id)
        val listaRotasView = rotas.map { rota ->
            viewMapper.map(rota)
        }
        return if (listaRotasView.isEmpty()) {
            ResponseEntity.noContent().build()
        } else {
            ResponseEntity.ok().body(listaRotasView)
        }
    }
}
