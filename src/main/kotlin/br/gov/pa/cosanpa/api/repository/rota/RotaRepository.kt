package br.gov.pa.cosanpa.api.repository.rota

import br.gov.pa.cosanpa.api.dominio.micromedicao.Rota
import org.springframework.data.jpa.repository.JpaRepository


interface RotaRepository: JpaRepository<Rota, Int> {
    fun findAllByLeituristaIdAndIndicadorUsoEqualsOrderByGrupoId(id: Int, indicadorUso: Int): List<Rota>
}
