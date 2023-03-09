package br.gov.pa.cosanpa.api.config.security.jwt

import br.gov.pa.cosanpa.api.dominio.seguranca.UsuarioDetail
import br.gov.pa.cosanpa.api.dominio.seguranca.credentials.Credentials
import com.fasterxml.jackson.databind.ObjectMapper
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.json.JSONObject
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

class JWTLoginFilter(
    private val authenticationManager: AuthenticationManager,
    private val jwtUtil: JWTUtil
) : UsernamePasswordAuthenticationFilter() {

    override fun attemptAuthentication(request: HttpServletRequest?, response: HttpServletResponse?): Authentication {
        val (login, senha) = ObjectMapper().readValue(request?.inputStream, Credentials::class.java)
        val token = UsernamePasswordAuthenticationToken(login, senha)
        return authenticationManager.authenticate(token)
    }

    override fun successfulAuthentication(
        request: HttpServletRequest?,
        response: HttpServletResponse?,
        chain: FilterChain?,
        authResult: Authentication?
    ) {
        val usuarioDetail = (authResult?.principal as UsuarioDetail)
        val generatedToken = jwtUtil.gerarToken(usuarioDetail)
        response?.contentType = "application/json;charset=UTF-8"
        response?.status = HttpServletResponse.SC_OK
        response?.writer?.write(
            JSONObject()
                .put("id", usuarioDetail.id)
                .put("nome", usuarioDetail.nome)
                .put("token", "$generatedToken")
                .toString()
        )
    }

}
