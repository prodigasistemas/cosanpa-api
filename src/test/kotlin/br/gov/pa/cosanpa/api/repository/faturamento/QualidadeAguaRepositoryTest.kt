package br.gov.pa.cosanpa.api.repository.faturamento

import br.gov.pa.cosanpa.api.repository.faturamento.qualidadeagua.QualidadeAguaRepository
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.TestPropertySource

@DataJpaTest
@TestPropertySource(locations = ["/application.yml"])
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class QualidadeAguaRepositoryTest {

    @Autowired
    private lateinit var repo: QualidadeAguaRepository

    @Test
    fun `dado uma referencia e id de sistema de abastecimento, entao retorna DTO`(){
        val dto = repo.obterQualidadeAgua(null, null, 4, 202212)
        assertNotNull(dto)
    }
}