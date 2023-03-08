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

    fun gerarToken(usuario: UsuarioDetail): String? {
        algoritmo = Algorithm.HMAC256(secret)
        val mapGrupo: MutableMap<String, String> = mutableMapOf()
        converterEmMap(usuario, mapGrupo)
        return try {
            JWT.create()
                .withIssuer("br.gov.pa COSANPA-API")
                .withSubject(usuario.username)
                .withClaim("grupos", mapGrupo)
                .withExpiresAt(dataExpiracao())
                .sign(algoritmo)
        } catch (exception: JWTCreationException) {
            throw RuntimeException("erro ao gerar token jwt", exception)
        }
    }

    private fun converterEmMap(
        usuario: UsuarioDetail,
        mapGrupo: MutableMap<String, String>
    ) {
        usuario.authorities.forEach { grupo ->
            mapGrupo.putIfAbsent(grupo.id.toString(), grupo.descricao!!)
        }
    }

    fun recuperaUsuario(tokenJWT: String?): String {
        algoritmo = Algorithm.HMAC256(secret)
        return try {
            JWT.require(algoritmo)
                .withIssuer("br.gov.pa COSANPA-API")
                .build()
                .verify(tokenJWT)
                .subject
        } catch (exception: JWTVerificationException) {
            throw TokenInvalidoException("Token JWT inválido ou expirado!")
        }
    }

    fun dataExpiracao(): Instant? {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"))
    }
}
