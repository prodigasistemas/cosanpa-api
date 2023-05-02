package br.gov.pa.cosanpa.api.repository.faturamento.consumo

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.TestPropertySource
import java.time.LocalDate

@DataJpaTest
@TestPropertySource(locations = ["/application.yml"])
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ConsumoTarifaCategoriaRepositoryTest {

    @Autowired
    private lateinit var repo: ConsumoTarifaCategoriaRepository
    
    @Test
    fun `dado um id de tarifa vigencia e categoria, entao retorna numero consumo minimo`() {
        val numeroConsumoMinimo = repo.obterConsumoMinimoTarifaCategoria(1, 1)
        assertNotNull(numeroConsumoMinimo)
    }

    @Test
    fun `dado os parametros corretos, entao retorna dados de ConsumoTarifa`(){
        val tarifaCategoriaDTO = repo.obterDadosConsumoTarifaPorDataVigencia(
            LocalDate.of(2022,11,28),
            1,
            1
        )
        assertNotNull(tarifaCategoriaDTO)
        println(tarifaCategoriaDTO)
    }

    @Test
    fun `dado os parametros corretos, entao retorna dados de ConsumoTarifa Proporcional`(){
        val tarifaCategoriaDTO = repo.obterDadosConsumoTarifaProporcional(
            LocalDate.now().minusDays(30),
            LocalDate.now(),
            1,
            1
        )
        assertNotNull(tarifaCategoriaDTO)
        println(tarifaCategoriaDTO)
    }
}