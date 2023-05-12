package br.gov.pa.cosanpa.api.repository.faturamento.consumo

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.TestPropertySource
import java.time.LocalDate

@DataJpaTest
@TestPropertySource(locations = ["/application.yml"])
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ConsumoTarifaRepositoryTest {

    @Autowired
    private lateinit var repo: ConsumoTarifaRepository

    @Test
    fun `dado um id de tarifa vigencia e categoria, entao retorna numero consumo minimo`() {
        val numeroConsumoMinimo = repo.obterConsumoMinimoTarifaCategoria(1, 1)
        Assertions.assertNotNull(numeroConsumoMinimo)
    }

    @Test
    fun `dado os parametros corretos, entao retorna dados de ConsumoTarifa`(){
        val tarifaCategoriaDTO = repo.obterDadosConsumoTarifaPorDataVigencia(
            LocalDate.of(2022,11,28),
            1,
            1
        )
        Assertions.assertNotNull(tarifaCategoriaDTO)
        println(tarifaCategoriaDTO)
    }

    @Test
    fun `dado os parametros corretos, entao retorna dados de ConsumoTarifa Proporcional`(){
        val tarifaCategoriaDTO = repo.obterDadosConsumoTarifaProporcional(
            LocalDate.now().minusYears(3),
            LocalDate.now(),
            1,
            1
        )
        Assertions.assertNotNull(tarifaCategoriaDTO)
        println(tarifaCategoriaDTO)
    }

    @Test
    fun `dado uma lista de ids de ConsumoTarifaCategoria, entao retorna dados referentes a ConsumoTarifaFaixa`(){
        val tarifaFaixaDTOList = repo.obterDados(listOf(150))
        Assertions.assertNotNull(tarifaFaixaDTOList)
        println(tarifaFaixaDTOList)
    }

    @Test
    fun `dado um id vigencia e uma data atual, retorna lista de dtos com vigencia mais atual`() {
        val lista = repo.obterDataVigente(1, LocalDate.now())
        Assertions.assertNotNull(lista)
        println(lista)
    }
}