package br.gov.pa.cosanpa.api.repository.faturamento.debito

import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.TestPropertySource

@DataJpaTest
@TestPropertySource(locations = ["/application.yml"])
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class DebitoRepositoryTest {

    @Autowired
    private lateinit var repo: DebitoRepository

    @Test
    fun `dado um id de conta, entao retorna lista de Dto de dados de Debito cobrado apenas parcelamentos`() {
        val debitoCobradoDTOList = repo.obterColecaoDebitoCobradoDeParcelamento(114740275)
        assertNotNull(debitoCobradoDTOList)
        println(debitoCobradoDTOList)
    }

    @Test
    fun `dado um id de conta, entao retorna lista de Dto de dados de Debito cobrado sem parcelamentos`() {
        val debitoCobradoDTOList = repo.obterDebitoCobradoNaoParcelamento(114740239)
        assertNotNull(debitoCobradoDTOList)
        println(debitoCobradoDTOList)
    }

    @Test
    fun `dado um id de debito tipo, entao retorna Dto de dados de Debito tipo`() {
        val debitoCreditoTipoDTO = repo.obterDadosDebitoTipo(1)
        assertNotNull(debitoCreditoTipoDTO)
        println(debitoCreditoTipoDTO)
    }


}