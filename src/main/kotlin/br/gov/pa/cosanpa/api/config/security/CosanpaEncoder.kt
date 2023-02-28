package br.gov.pa.cosanpa.api.config.security

import br.gov.pa.cosanpa.api.extensions.converteParaHash
import org.springframework.security.crypto.password.PasswordEncoder

class CosanpaEncoder : PasswordEncoder {
    override fun encode(rawPassword: CharSequence?): String {
        return rawPassword.converteParaHash()
    }

    override fun matches(rawPassword: CharSequence?, encodedPassword: String?): Boolean {
        return rawPassword.converteParaHash() == encodedPassword
    }
}