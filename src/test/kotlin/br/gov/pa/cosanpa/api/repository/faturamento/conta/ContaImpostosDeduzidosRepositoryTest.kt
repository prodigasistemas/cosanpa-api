package br.gov.pa.cosanpa.api.repository.faturamento.conta

import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.TestPropertySource

@DataJpaTest
@TestPropertySource(locations = ["/application.yml"])
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ContaImpostosDeduzidosRepositoryTest {

    @Autowired
    private lateinit var repo: ContaRepository

    @Test
    fun `dado um id de conta, entao retorna lista de Dto de Dados`() {
        val dtos = repo.obterDadosContaImpostosDeduzidos(114936531)
        assertNotNull(dtos)
    }

    @Test
    fun `dado um id de imposto tipo, entao retorna Dto de Dados`() {
        val dto = repo.obterDadosImpostoTipo(1)
        assertNotNull(dto)
        println(dto)
    }

}
