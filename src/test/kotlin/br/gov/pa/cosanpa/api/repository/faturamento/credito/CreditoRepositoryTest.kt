package br.gov.pa.cosanpa.api.repository.faturamento.credito

import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.TestPropertySource

@DataJpaTest
@TestPropertySource(locations = ["/application.yml"])
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class CreditoRepositoryTest {

    @Autowired
    private lateinit var repo: CreditoRepository

    @Test
    fun `dado um id de conta, entao retorna lista de Dto de dados de Debito cobrado`() {
        val creditoRealizadoDTOS = repo.obterCreditosRealizadosPorContaId(114740225)
        assertNotNull(creditoRealizadoDTOS)
        assertFalse(creditoRealizadoDTOS.isEmpty())
        println(creditoRealizadoDTOS)
    }

    @Test
    fun `dado um id de credito tipo, entao retorna Dto de dados de Debito tipo`() {
        val debitoCreditoTipoDTO = repo.obterDadosCreditoTipo(871)
        assertNotNull(debitoCreditoTipoDTO)
        println(debitoCreditoTipoDTO)
    }
}