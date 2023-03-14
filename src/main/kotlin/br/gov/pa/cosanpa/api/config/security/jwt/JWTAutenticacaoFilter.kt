package br.gov.pa.cosanpa.api.config.security.jwt

import br.gov.pa.cosanpa.api.service.seguranca.usuario.UsuarioService
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter


@Component
class JWTAutenticacaoFilter(
    private val jwtUtil: JWTUtil,
    private val usuarioService: UsuarioService
) : OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val token = request.getHeader("Authorization")
        val tokenDetail = getTokenDetail(token)
        jwtUtil.recuperaUsuario(tokenDetail).let { usuario ->
            val usuarioDetail = usuarioService.loadUserByUsername(usuario)
            val authentication = UsernamePasswordAuthenticationToken(
                usuarioDetail.username,
                usuarioDetail.password,
                usuarioDetail.authorities
            )
            SecurityContextHolder.getContext().authentication = authentication
        }
        filterChain.doFilter(request, response)
    }

    private fun getTokenDetail(token: String?): String? {
        return token?.let { jwt ->
            jwt.startsWith("Bearer ")
            jwt.substring(7)
        }
    }

}
