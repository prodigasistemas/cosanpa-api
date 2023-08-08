package br.gov.pa.cosanpa.api.repository.faturamento

import br.gov.pa.cosanpa.api.dominio.faturamento.situacao.FaturamentoSituacaoTipo
import br.gov.pa.cosanpa.api.repository.faturamento.situacao.FaturamentoSituacaoRepository
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.TestPropertySource

@DataJpaTest
@TestPropertySource(locations = ["/application.yml"])
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class FaturamentoSituacaoRepositoryTest {

    @Autowired
    private lateinit var repo: FaturamentoSituacaoRepository

    @Test
    fun `dado uma id de FaturamentoSituacaoTipo, entao retorna Dto com informacoes referentes`(){
        val dto = repo.obterDadosTipo(FaturamentoSituacaoTipo.FATURAR_NORMAL)
        assertNotNull(dto)
    }

    @Test
    fun `dado um id de imovel valido, entao retorna dto de faturamento situacao historico`() {
        val dto = repo.obterHistoricoPorImovelSemRetirada(3661504, 202303)
        assertNotNull(dto)
        println(dto)
    }

    @Test
    fun `dado um id de faturamento situacao tipo, entao retorna dto de faturamento situacao tipo`() {
        val dto = repo.obterDadosTipo(11)
        assertNotNull(dto)
        println(dto)
    }



}