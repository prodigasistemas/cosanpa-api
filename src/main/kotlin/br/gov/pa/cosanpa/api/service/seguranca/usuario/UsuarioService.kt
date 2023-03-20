package br.gov.pa.cosanpa.api.service.seguranca.usuario

import br.gov.pa.cosanpa.api.dominio.seguranca.UsuarioDetail
import br.gov.pa.cosanpa.api.exceptions.NaoEncontradoException
import br.gov.pa.cosanpa.api.repository.seguranca.usuario.UsuarioRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class UsuarioService(
    private val repository: UsuarioRepository
) : UserDetailsService {

    override fun loadUserByUsername(login: String?): UserDetails {
        val usuario = repository.findByLogin(login) ?: throw NaoEncontradoException("Usuário não encontrado")
        return UsuarioDetail(usuario)
    }
}