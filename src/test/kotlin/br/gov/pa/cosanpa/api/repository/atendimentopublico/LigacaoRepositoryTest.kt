package br.gov.pa.cosanpa.api.repository.atendimentopublico

import br.gov.pa.cosanpa.api.repository.atendimentopublico.ligacaoagua.LigacaoRepository
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.TestPropertySource

@DataJpaTest
@TestPropertySource(locations = ["/application.yml"])
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class LigacaoRepositoryTest {

    @Autowired
    private lateinit var ligacaoRepository: LigacaoRepository

    @Test
    fun `dado o id da situacao, entao retorna dto de dados de ligacao agua`() {
        val situacaoDTO = ligacaoRepository.obterDadosLigacaoAguaSituacao(1)
        assertNotNull(situacaoDTO)
        assertEquals(situacaoDTO.descricao, "POTENCIAL")
    }

    @Test
    fun `dado o id da situacao, entao retorna dto de dados de ligacao esgoto`() {
        val situacaoDTO = ligacaoRepository.obterDadosLigacaoEsgotoSituacao(1)
        assertNotNull(situacaoDTO)
        assertEquals(situacaoDTO.descricao, "POTENCIAL")
    }

    @Test
    fun `dado o id de ligacao agua existente, entao retorna dados de ligacao agua`() {
        val ligacaoAguaDTO = ligacaoRepository.obterDadosLigacaoAgua(2432358)
        assertNotNull(ligacaoAguaDTO)
    }

    @Test
    fun `dado o id de ligacao agua nao existente, entao retorna null`() {
        val ligacaoAguaDTO = ligacaoRepository.obterDadosLigacaoAgua(5463912)
        assertNull(ligacaoAguaDTO)
    }

    @Test
    fun `dado o id de ligacao esgoto existente, entao retorna dados de ligacao esgoto`() {
        val ligacaoEsgotoDTO = ligacaoRepository.obterDadosLigacaoEsgoto(3177998)
        assertNotNull(ligacaoEsgotoDTO)
    }

    @Test
    fun `dado o id de ligacao esgoto nao existente, entao retorna null`() {
        val ligacaoEsgotoDTO = ligacaoRepository.obterDadosLigacaoEsgoto(7007493)
        assertNull(ligacaoEsgotoDTO)
    }
}