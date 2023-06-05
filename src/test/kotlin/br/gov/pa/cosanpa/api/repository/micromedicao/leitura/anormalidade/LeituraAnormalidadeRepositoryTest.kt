package br.gov.pa.cosanpa.api.repository.micromedicao.leitura.anormalidade

import br.gov.pa.cosanpa.api.dominio.micromedicao.leitura.anormalidade.LeituraAnormalidadeConsumo
import br.gov.pa.cosanpa.api.dominio.micromedicao.leitura.anormalidade.LeituraAnormalidadeLeitura
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.TestPropertySource

@DataJpaTest
@TestPropertySource(locations = ["/application.yml"])
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class LeituraAnormalidadeRepositoryTest {

    @Autowired
    private lateinit var repo: LeituraAnormalidadeRepository

    @Test
    fun `dado o id de anormalidade leitura, retorna dto de dados`() {
        val dto = repo.obterDadosLeituraAnormalidadeLeitura(LeituraAnormalidadeLeitura.ANTERIOR_MAIS_CONSUMO)
        assertNotNull(dto)
        assertEquals("ANTERIOR + CONSUMO", dto.descricao)
    }

    @Test
    fun `dado o id de anormalidade consumo, retorna dto de dados`() {
        val dto = repo.obterDadosLeituraAnormalidadeConsumo(LeituraAnormalidadeConsumo.INFORMAR_O_CONSUMO)
        assertNotNull(dto)
        assertEquals("INFORMAR O CONSUMO", dto.descricao)
    }
}