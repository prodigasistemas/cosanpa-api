package br.gov.pa.cosanpa.api.repository.faturamento.consumo

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.TestPropertySource

@DataJpaTest
@TestPropertySource(locations = ["/application.yml"])
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ConsumoTarifaCategoriaRepositoryTest {

    @Autowired
    private lateinit var repo: ConsumoTarifaCategoriaRepository
    
    @Test
    fun `dado um ide de tarifa vigencia e categoria, entao retorna numero consumo minimo`() {
        val numeroConsumoMinimo = repo.obterConsumoMinimoTarifaCategoria(1, 1)
        Assertions.assertNotNull(numeroConsumoMinimo)
    }
}