package br.gov.pa.cosanpa.api.repository.rota

import br.gov.pa.cosanpa.api.dominio.micromedicao.Rota
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query


interface RotaRepository: JpaRepository<Rota, Int> {

    @Query("SELECT r FROM Rota r " +
           "INNER JOIN r.leiturista l " +
           "INNER JOIN l.usuario u " +
           "WHERE u.id = :id")
    fun buscarPorUsuario(id: Int): List<Rota>
}
