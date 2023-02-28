package br.gov.pa.cosanpa.api.config.security

import br.gov.pa.cosanpa.api.config.security.jwt.JWTAutenticacaoFilter
import br.gov.pa.cosanpa.api.config.security.jwt.JWTLoginFilter
import br.gov.pa.cosanpa.api.config.security.jwt.JWTUtil
import jakarta.servlet.http.HttpServletResponse
import org.json.JSONObject
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*


@Configuration
@EnableWebSecurity
class SecurityConfiguration (
    private val configuration: AuthenticationConfiguration,
    private val jwtAuthenticationFilter: JWTAutenticacaoFilter,
    private val jwtUtil: JWTUtil
) {

    @Bean
    @Throws(Exception::class)
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain? {
        return http.csrf()?.disable()
            ?.sessionManagement()?.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            ?.and()
            ?.authorizeHttpRequests()
            ?.requestMatchers(HttpMethod.POST, "/login")?.permitAll()
            ?.anyRequest()?.authenticated()
            ?.and()
            ?.addFilterBefore(
                JWTLoginFilter(configuration.authenticationManager, jwtUtil),
                UsernamePasswordAuthenticationFilter::class.java
            )
            ?.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter::class.java)
            ?.exceptionHandling()
            ?.authenticationEntryPoint { _, response, _ ->
                tratamentoFalhaAutenticacao(response)
            }
            ?.and()
            ?.build()
    }

    private fun tratamentoFalhaAutenticacao(
        response: HttpServletResponse) {
        response.contentType = "application/json;charset=UTF-8"
        response.status = HttpServletResponse.SC_UNAUTHORIZED
        response.writer.write(
            JSONObject()
                .put("data", LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm")))
                .put("mensagem", "Falha na Autenticacao")
                .toString()
        )
    }

    @Bean
    @Throws(Exception::class)
    fun authenticationManager(configuration: AuthenticationConfiguration): AuthenticationManager? {
        return configuration.authenticationManager
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder? {
        return CosanpaEncoder()
    }
}