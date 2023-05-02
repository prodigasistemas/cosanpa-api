package br.gov.pa.cosanpa.api.repository.faturamento.consumo

import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.TestPropertySource

@DataJpaTest
@TestPropertySource(locations = ["/application.yml"])
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ConsumoTarifaFaixaRepositoryTest {

    @Autowired
    private lateinit var repo: ConsumoTarifaFaixaRepository

    @Test
    fun `dado uma lista de ids de ConsumoTarifaCategoria, entao retorna dados referentes a ConsumoTarifaFaixa`(){
        val tarifaFaixaDTOList = repo.obterDados(listOf(150))
        assertNotNull(tarifaFaixaDTOList)
        println(tarifaFaixaDTOList)
    }
}