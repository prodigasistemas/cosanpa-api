package br.gov.pa.cosanpa.api.repository.arrecadacao.debitoautomatico

import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.TestPropertySource

@DataJpaTest
@TestPropertySource(locations = ["/application.yml"])
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class DebitoAutomaticoRepositoryTest {

    @Autowired
    private lateinit var repo: DebitoAutomaticoRepository

    @Test
    fun `dado um id de imovel que nao possui debito automatico, entao retorna DTO null`() {
        val dto = repo.obterDadosDebitoAutomaticoPorImovelId(2326426)
        assertNull(dto)
    }

    @Test
    fun `dado um id de imovel que possui debito, entao retorna DTO de dados validos`() {
        val dto = repo.obterDadosDebitoAutomaticoPorImovelId(3640469)
        assertNotNull(dto)
    }
}