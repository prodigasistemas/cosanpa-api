package br.gov.pa.cosanpa.api.repository.faturamento.credito

import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.TestPropertySource

@DataJpaTest
@TestPropertySource(locations = ["/application.yml"])
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class CreditoRealizadoRepositoryTest {

    @Autowired
    private lateinit var repo: CreditoRealizadoRepository

    @Test
    fun `dado um id de conta, entao retorna lista de DTO de dados de Debito cobrado`() {
        val creditoRealizadoDTOS = repo.obterCreditosRealizadosPorContaId(114936531)
        assertNotNull(creditoRealizadoDTOS)
        println(creditoRealizadoDTOS)
    }
}