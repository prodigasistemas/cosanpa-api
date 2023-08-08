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
class HidrometroRepositoryTest {

    @Autowired
    private lateinit var repo: HidrometroRepository

    @Test
    fun `dado um id de imovel, retorna data de instalacao do hidrometro caso exista`() {
        val dataInstalacaoHIdrometro = repo.obterDataInstalacaoHIdrometroPorImovelId(2474506)
        assertNotNull(dataInstalacaoHIdrometro)
        dataInstalacaoHIdrometro?.let { println(it) }
    }

    @Test
    fun `dado um id de imovel, retorna dto de dados do hidrometro instalacao historico caso exista`() {
        val dto = repo.obterDadosHidrometroInstalacaoHidrometroPorImovel(3036707)
        assertNotNull(dto)
        println(dto)
    }

    @Test
    fun `dado um id de hidrometro, retorna dto de dados do hidrometro `() {
        val dto = repo.obterDadosHidrometro(11724986)
        assertNotNull(dto)
        println(dto)
    }

    @Test
    fun `dado um id de hidrometro protecao, retorna dto de dados do hidrometro protecao`() {
        val dto = repo.obterDadosHidrometroProtecao(1)
        assertNotNull(dto)
        println(dto)
    }

    @Test
    fun `dado um id de hidrometro local instalacao, retorna dto de dados do hidrometro local instalacao`() {
        val dto = repo.obterDadosHidrometroLocalInstalacao(1)
        assertNotNull(dto)
        println(dto)
    }

    @Test
    fun `dado um id de rateio tipo, retorna dto de dados do rateio tipo`() {
        val dto = repo.obterDadosRateioTipo(1)
        assertNotNull(dto)
        println(dto)
    }
}