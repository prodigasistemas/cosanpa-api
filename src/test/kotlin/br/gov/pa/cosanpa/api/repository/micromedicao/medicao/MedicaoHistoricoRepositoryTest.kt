package br.gov.pa.cosanpa.api.repository.micromedicao.medicao

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.TestPropertySource

@DataJpaTest
@TestPropertySource(locations = ["/application.yml"])
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class MedicaoHistoricoRepositoryTest {

    @Autowired
    private lateinit var repo: MedicaoHistoricoRepository

    @Test
    fun `dado o id do imovel e referencia, entao retorna data leitura atual faturamento`() {
        val dataLeituraAnterior = repo.obterMaiorDataLeituraAtualPorImovelId(2085071, 202202)
        dataLeituraAnterior?.let { println(it) }
    }

    @Test
    fun `dado o id do imovel, entao retorna dados de Medicao Historico Dto baseados no idImovel e idLigacaoAgua nula`() {
        val dadosMedicaoHistorico = repo.obterDadosMedicaoHistoricoPorImovelId(
            202203,
            3036707,
        )
        assertNotNull(dadosMedicaoHistorico)
        dadosMedicaoHistorico?.let { println(it) }
    }

    @Test
    fun `dado o id do imovel nulo e idLIgacaoAgua nao, entao retorna dados de Medicao Historico Dto idLigacaoAgua nula`() {
        val dadosMedicaoHistorico = repo.obterDadosMedicaoHistoricoPorImovelId(
            202203,
            2085071
        )
        assertNotNull(dadosMedicaoHistorico)
        dadosMedicaoHistorico?.let { println(it) }
    }

    @Test
    fun `dado o id de medicao tipo, entao retorna dados de Medicao tipo`() {
        val dto = repo.obterDadosMedicaoTipo(1)
        assertNotNull(dto)
        assertEquals("LIGACAO DE AGUA", dto.descricao)
    }
}