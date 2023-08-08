package br.gov.pa.cosanpa.api.repository.cadastro.imovel.localidade

import br.gov.pa.cosanpa.api.extensions.cadastro.endereco.formatarEndereco
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
    fun `dado um id de localidade 1, entao retorna um Dto nao nulo com informacoes nulas`() {
        val dto = repo.obterDadosEnderecoAtendimento(1)
        assertNotNull(dto)
        assertEquals("null",dto.formatarEndereco())
    }

    @Test
    fun `dado um id de localidade, entao retorna um Dto com informações de localidade`() {
        val dto = repo.obterDadosLocalidade(1)
        assertNotNull(dto)
    }

    @Test
    fun `dado um id de Gerencia Regional, entao retorna Dto generico com dados de id e nome`() {
        val dto = repo.obterDadosGerenciaRegional(1)
        assertNotNull(dto)
        assertEquals("METROPOLITANA", dto.descricao)
    }

    @Test
    fun `dado um id de Municipio, entao retorna Dto com dados`() {
        val dto = repo.obterDadosMunicipio(33)
        assertNotNull(dto)
        assertEquals("BELEM", dto.nome)
        assertEquals(91, dto.ddd)
    }
}