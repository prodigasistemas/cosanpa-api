package br.gov.pa.cosanpa.api.business.faturamento.mensagemconta

import br.gov.pa.cosanpa.api.dto.faturamento.conta.ContaMensagemDTO
import br.gov.pa.cosanpa.api.service.faturamento.conta.ContaService
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test

class ContaServiceMensagemContaTest {

    private val contaService: ContaService = mockk {
        every {
            obterMensagemContaView(
                referencia = any(),
                idFaturamentoGrupo = null,
                idGerenciaRegional = any(),
                idLocalidade = any(),
                idSetorComercial = any(),
                idQuadra = any()
            )
        } returns "MENSAGEM TESTE GerenciaLocalidadeSetorQuadra 01"


        every {
            obterMensagemContaView(
                referencia = any(),
                idFaturamentoGrupo = any(),
                idGerenciaRegional = null,
                idLocalidade = null,
                idSetorComercial = null,
                idQuadra = null
            )
        } returns "MENSAGEM TESTE Grupo 01"


        every {
            obterMensagemContaView(
                referencia = any(),
                idFaturamentoGrupo = null,
                idGerenciaRegional = null,
                idLocalidade = null,
                idSetorComercial = null,
                idQuadra = null
            )
        } returns "MENSAGEM TESTE Referencia 01"


        every { obterMensagemContaView(0, 0, 0, 0, 0, 0) } returns null
    }

    private var mensagens: String? = null

    @Test
    fun `dado os parametros de gerencia, localidade, setor, quadra, entao retorna mensagem GerenciaLocalidadeSetorQuadra`() {
        mensagens = contaService.obterMensagemContaView(
            referencia = 202305,
            idFaturamentoGrupo = null,
            idGerenciaRegional = 1,
            idLocalidade = 1,
            idSetorComercial = 1,
            idQuadra = 2020
        )
        assertNotNull(mensagens)
        mensagens?.let {
            assertEquals("MENSAGEM TESTE GerenciaLocalidadeSetorQuadra 01", it)
        }
    }

    @Test
    fun `dado os parametros de referencia e grupo, entao retorna mensagem grupo`() {
        mensagens = contaService.obterMensagemContaView(
            referencia = 202305,
            idFaturamentoGrupo = 202,
            idGerenciaRegional = null,
            idLocalidade = null,
            idSetorComercial = null,
            idQuadra = null
        )

        assertNotNull(mensagens)
        mensagens?.let {
            assertEquals("MENSAGEM TESTE Grupo 01", it)
        }
    }

    @Test
    fun `dado os parametros de referencia, entao retorna mensagem Referencia`() {
        mensagens = contaService.obterMensagemContaView(
            referencia = 202305,
            idFaturamentoGrupo = null,
            idGerenciaRegional = null,
            idLocalidade = null,
            idSetorComercial = null,
            idQuadra = null
        )

        assertNotNull(mensagens)
        mensagens?.let {
            assertEquals("MENSAGEM TESTE Referencia 01", it)
        }
    }

}