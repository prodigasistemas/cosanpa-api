package br.gov.pa.cosanpa.api.repository.arrecadacao.banco

import br.gov.pa.cosanpa.api.repository.arrecadacao.banco.BancoRepository
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
class BancoRepositoryTeste {

    @Autowired
    private lateinit var repo: BancoRepository

    @Test
    fun `dado o id do banco, retorna Dto de dados`() {
        val dto = repo.obterDadosBanco(1)
        assertNotNull(dto)
    }

    @Test
    fun `dado o id da agencia, retorna Dto de dados da agencia com banco`() {
        // Id Agencia: 407, Banco Bradesco: 237, Codigo Agencia: 3109
        val agenciaDTO = repo.obterDadosAgencia(407)
        assertNotNull(agenciaDTO)
        assertEquals("3109", agenciaDTO.codigo)
        assertEquals(237, agenciaDTO.idBanco)
    }
}