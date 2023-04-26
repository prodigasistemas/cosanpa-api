package br.gov.pa.cosanpa.api.repository.micromedicao.hidrometro

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.TestPropertySource

@DataJpaTest
@TestPropertySource(locations = ["/application.yml"])
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class HidrometroInstalacaoHistoricoRepositoryTest {

    @Autowired
    private lateinit var repo: HidrometroInstalacaoHistoricoRepository

    @Test
    fun `dado um id de imovel, retorna data de instalacao do hidrometro caso exista`() {
        val dataInstalacaoHIdrometro = repo.obterDataInstalacaoHIdrometroPorImovelId(2474506)
        assertNotNull(dataInstalacaoHIdrometro)
        dataInstalacaoHIdrometro?.let { println(it) }
    }

    @Test
    fun `dado um id de imovel, retorna dto de dados do hidrometro caso exista`() {
        val dto = repo.obterDadosHidrometro(2474506)
        assertNotNull(dto)
        println(dto)
    }
}