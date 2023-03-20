package br.gov.pa.cosanpa.api.repository.faturamento

import br.gov.pa.cosanpa.api.dominio.faturamento.FaturamentoSituacaoTipo
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.TestPropertySource

@DataJpaTest
@TestPropertySource(locations = ["/application.yml"])
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class FaturamentoSituacaoTipoRepositoryTest {

    @Autowired
    private lateinit var repo: FaturamentoSituacaoTipoRepository

    @Test
    fun `dado uma id de FaturamentoSituacaoTipo, entao retorna DTO com informacoes referentes`(){
        val dto = repo.obterFaturamentoSituacaoTipo(FaturamentoSituacaoTipo.FATURAR_NORMAL)
        Assertions.assertNotNull(dto)
    }
}