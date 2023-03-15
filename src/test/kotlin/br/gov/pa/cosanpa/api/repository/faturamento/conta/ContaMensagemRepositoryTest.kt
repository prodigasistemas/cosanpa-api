package br.gov.pa.cosanpa.api.repository.faturamento.conta

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
        assertEquals("A COSANPA INFORMA QUE, CONFORME DECISAO JUDICIAL, SUA FATURA VOLTARA A SER EMITIDA.", mensagem3Partes.descricaoContaMensagem01)
        assertEquals("NESTE PRIMEIRO MES, NAO HAVERA COBRANCA, E SERA APENAS COLETADA A LEITURA DO MEDIDOR.", mensagem3Partes.descricaoContaMensagem02)
        assertEquals("A PARTIR DO PROXIMO MES SUA FATURA SERA EMITIDA PELO SEU CONSUMO EFETIVO.", mensagem3Partes.descricaoContaMensagem03)
    }

    @Test
    fun `dado uma faturamento grupo id e outros parametros null, retorna mensagem da conta`() {
        val mensagem3Partes = repo.obterContaMensagem3Partes(idQuadra = null,
            idFaturamentoGrupo = 654,
            amReferencia = 202206,
            idGerenciaRegional = null,
            idLocalidade = null,
            idSetorComercial = null)

        assertNotNull(mensagem3Partes)
        assertEquals("\"CENSO 2022. A PARTIR DE AGOSTO, ATENDA O RECENSEADOR", mensagem3Partes.descricaoContaMensagem01)
        assertEquals("DO IBGE E RESPONDA CORRETAMENTE.\"", mensagem3Partes.descricaoContaMensagem02)
        assertNull(mensagem3Partes.descricaoContaMensagem03)
    }
}