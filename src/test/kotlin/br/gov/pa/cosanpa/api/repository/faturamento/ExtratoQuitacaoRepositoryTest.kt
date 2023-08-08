package br.gov.pa.cosanpa.api.repository.faturamento

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
class ExtratoQuitacaoRepositoryTest {

    @Autowired
    private lateinit var repo: ExtratoQuitacaoRepository

    @Test
    fun `dado um idImovel e anoReferencia validos, entao retorna um ExtratoQuitacaoDto`(){
        val dto = repo.obterExtratoQuitacaoImovel(2082292, 2010)
        assertNotNull(dto)
    }

    @Test
    fun `dado um idImovel e anoReferencia invalidos, entao retorna um null`(){
        val dto = repo.obterExtratoQuitacaoImovel(7007493, 2010)
        assertNull(dto)
    }
}