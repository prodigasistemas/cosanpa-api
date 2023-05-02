package br.gov.pa.cosanpa.api.repository.faturamento.consumo

import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.TestPropertySource
import java.time.LocalDate

@DataJpaTest
@TestPropertySource(locations = ["/application.yml"])
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ConsumoTarifaVigenciaRepositoryTest {

    @Autowired
    private lateinit var repo: ConsumoTarifaVigenciaRepository

    @Test
    fun `dado um id vigencia e uma data atual, retorna lista de dtos com vigencia mais atual`() {
        val lista = repo.obterDataVigente(1, LocalDate.now())
        assertNotNull(lista)
        println(lista)
    }
}