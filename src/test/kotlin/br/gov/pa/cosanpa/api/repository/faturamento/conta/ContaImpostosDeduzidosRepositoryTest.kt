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
    private lateinit var repo: ContaImpostosDeduzidosRepository

    @Test
    fun `dado um id de conta, entao retorna lista de DTO de Dados`() {
        val dtos = repo.obterDadosContaImpostosDeduzidosDTO(114936531)
        assertNotNull(dtos)
    }

}
