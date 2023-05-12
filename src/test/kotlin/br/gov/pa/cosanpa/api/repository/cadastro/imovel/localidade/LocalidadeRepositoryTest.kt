package br.gov.pa.cosanpa.api.repository.cadastro.imovel.localidade

import br.gov.pa.cosanpa.api.repository.cadastro.imovel.localidade.LocalidadeRepository
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
class LocalidadeRepositoryTest {

    @Autowired
    private lateinit var repo: LocalidadeRepository

    @Test
    fun `dado um id de localidade, entao retorna um DTO com informações de endereco`() {
        val dto = repo.obterDadosEnderecoAtendimento(1)
        assertNotNull(dto)
    }

    @Test
    fun `dado um id de localidade, entao retorna um DTO com informações de localidade`() {
        val dto = repo.obterDadosLocalidade(1)
        assertNotNull(dto)
    }

    @Test
    fun `dado um id de Gerencia Regional, entao retorna DTO generico com dados de id e nome`() {
        val dto = repo.obterDadosGerenciaRegional(1)
        assertNotNull(dto)
        assertEquals("METROPOLITANA", dto.descricao)
    }
}