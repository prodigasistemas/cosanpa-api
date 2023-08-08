package br.gov.pa.cosanpa.api.repository.faturamento.conta

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.TestPropertySource

@DataJpaTest
@TestPropertySource(locations = ["/application.yml"])
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ComunicadoEmitirContaTest {

    @Autowired
    private lateinit var repo: ContaRepository

    @Test
    fun `dado os parametros passados corretamente, retorna quantidade de comunicados`() {
        val resultado = repo.quantidadeComunicadoEmitido(2035375, 2, 1, 202202)
        assertEquals(1, resultado.toInt())
    }

    @Test
    fun `dado os parametros passados de imovel sem comunicados, retorna 0`() {
        val resultado = repo.quantidadeComunicadoEmitido(7389353, 1, 1, 202212)
        assertEquals(0, resultado.toInt())
    }
}