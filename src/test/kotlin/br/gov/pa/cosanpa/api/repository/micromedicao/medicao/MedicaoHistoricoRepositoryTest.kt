package br.gov.pa.cosanpa.api.repository.micromedicao.medicao

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
    fun `dado o id do imovel nao nulo, retorna dados de Medicao Historico DTO baseados no idImovel`() {
        val dataLeituraAnterior = repo.obterDataLeituraAnteriorPorImovelId(3036707)
        assertNotNull(dataLeituraAnterior)
        dataLeituraAnterior?.let { println(it) }
    }
}