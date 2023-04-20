package br.gov.pa.cosanpa.api.dominio.seguranca

import org.springframework.security.core.userdetails.UserDetails

class UsuarioDetail(private val usuario: Usuario) : UserDetails {

    val id: Int = usuario.id

    val nome: String = usuario.nome

    override fun getAuthorities() = null

    override fun getPassword() = usuario.senha

    override fun getUsername() = usuario.login

    override fun isAccountNonExpired() = true

    override fun isAccountNonLocked() = true

    override fun isCredentialsNonExpired() = true

    override fun isEnabled() = true
}