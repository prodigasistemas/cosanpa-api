package br.gov.pa.cosanpa.api.repository.faturamento

import br.gov.pa.cosanpa.api.dominio.faturamento.atividade.FaturamentoAtividade
import br.gov.pa.cosanpa.api.repository.faturamento.atividade.FaturamentoAtividadeRepository
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.TestPropertySource

@DataJpaTest
@TestPropertySource(locations = ["/application.yml"])
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class FaturamentoAtividadeCronogramaRepositoryTest {

    @Autowired
    private lateinit var repo: FaturamentoAtividadeRepository

    @Test
    fun `dado uma id de FaturamentoSituacaoTipo, entao retorna Dto com informacoes referentes`(){
        val data = repo.obterDataPrevista(201, FaturamentoAtividade.EFETUAR_LEITURA, 202211)
        Assertions.assertNotNull(data)
        println(data)
    }
}