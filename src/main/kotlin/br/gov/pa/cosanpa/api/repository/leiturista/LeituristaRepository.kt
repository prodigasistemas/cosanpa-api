package br.gov.pa.cosanpa.api.repository.leiturista

import br.gov.pa.cosanpa.api.domain.leiturista.Leiturista
import org.springframework.data.jpa.repository.JpaRepository

interface LeituristaRepository : JpaRepository<Leiturista, Int> {
    fun findAllByUsuarioId(usuarioId: Int): List<Leiturista>
}