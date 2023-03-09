package br.gov.pa.cosanpa.api.config.security.jwt

import br.gov.pa.cosanpa.api.dominio.seguranca.UsuarioDetail
import br.gov.pa.cosanpa.api.exceptions.TokenInvalidoException
import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.exceptions.JWTCreationException
import com.auth0.jwt.exceptions.JWTVerificationException
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneOffset

@Component
class JWTUtil {

    @Value("\${api.security.token.secret}")
    private lateinit var secret: String
    private lateinit var algoritmo: Algorithm

    fun gerarToken(usuario: UsuarioDetail) = try {
        algoritmo = Algorithm.HMAC256(secret)

        JWT.create()
            .withIssuer("br.gov.pa COSANPA-API")
            .withSubject(usuario.username)
            .withExpiresAt(dataExpiracao)
            .sign(algoritmo)
    } catch (exception: JWTCreationException) {
        throw RuntimeException("erro ao gerar token jwt", exception)
    }

    fun recuperarUsuario(tokenJWT: String?) = try {
        algoritmo = Algorithm.HMAC256(secret)

        JWT.require(algoritmo)
            .withIssuer("br.gov.pa COSANPA-API")
            .build()
            .verify(tokenJWT)
            .subject
    } catch (exception: JWTVerificationException) {
        throw TokenInvalidoException("Token JWT inválido ou expirado!")
    }

    val dataExpiracao: Instant? = LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"))
}
