package br.gov.pa.cosanpa.api.extensions

import org.apache.tomcat.util.codec.binary.Base64
import java.nio.charset.Charset
import java.security.MessageDigest

fun CharSequence?.converteParaHash(
    tipoAlgoritmo: String = "SHA"
): String {
    val md = MessageDigest.getInstance(tipoAlgoritmo)
    md.update(this.toString().toByteArray(Charset.forName("UTF-8")))
    val bytes = md.digest()
    return String(Base64.encodeBase64(bytes, false))
}