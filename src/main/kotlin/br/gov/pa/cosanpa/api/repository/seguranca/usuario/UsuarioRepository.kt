package br.gov.pa.cosanpa.api.repository.seguranca.usuario

import br.gov.pa.cosanpa.api.dominio.seguranca.Usuario
import org.springframework.data.jpa.repository.JpaRepository

interface UsuarioRepository : JpaRepository<Usuario, Int> {

    fun findByLogin(login: String?): Usuario?
}
