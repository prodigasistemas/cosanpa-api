package br.gov.pa.cosanpa.api.service.micromedicao.rota

import br.gov.pa.cosanpa.api.repository.micromedicao.rota.RotaRepository
import br.gov.pa.cosanpa.api.view.micromedicao.rota.RotaView
import br.gov.pa.cosanpa.api.view.micromedicao.rota.RotaViewMapper
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class RotaService(
    private val repository: RotaRepository,
    private val viewMapper: RotaViewMapper
) {

    fun buscarRotasPorUsuario(id: Int): ResponseEntity<List<RotaView>> {
        val rotas = repository.obterRotasPorUsuario(id)
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
