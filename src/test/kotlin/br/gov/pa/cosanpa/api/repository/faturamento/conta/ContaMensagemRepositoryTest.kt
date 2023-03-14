package br.gov.pa.cosanpa.api.repository.faturamento.conta

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
class ContaMensagemRepositoryTest {

    @Autowired
    private lateinit var repo: ContaMensagemRepository

    @Test
    fun `dado uma quadra id e grupo null, retorna mensagem da conta`() {
        val mensagem3Partes = repo.obterContaMensagem3Partes(idQuadra = 3347,
                idFaturamentoGrupo = null,
                amReferencia = 202212,
                idGerenciaRegional = 1,
                idLocalidade = 1,
                idSetorComercial = 6)

        assertNotNull(mensagem3Partes)
    }
}