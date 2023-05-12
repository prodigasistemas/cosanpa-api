package br.gov.pa.cosanpa.api.repository.micromedicao.rota

import br.gov.pa.cosanpa.api.dominio.micromedicao.rota.Rota
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query


interface RotaRepository: JpaRepository<Rota, Int> {

    @Query("SELECT r FROM Rota r " +
           "INNER JOIN r.leiturista l " +
           "INNER JOIN l.usuario u " +
           "WHERE u.id = :id")
    fun obterRotasPorUsuario(id: Int): List<Rota>

}
