package br.gov.pa.cosanpa.api.business.faturamento

import br.gov.pa.cosanpa.api.dto.faturamento.ExtratoQuitacaoDTO
import br.gov.pa.cosanpa.api.repository.faturamento.ExtratoQuitacaoRepository
import br.gov.pa.cosanpa.api.service.faturamento.ExtratoQuitacaoService
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class ExtratoQuitacaoServiceTest {

    private val repository: ExtratoQuitacaoRepository = mockk {
        every { obterExtratoQuitacaoImovel(2096498, 2010) } returns ExtratoQuitacaoDTO(
            id = 91427,
            anoReferencia = 2010,
            indicadorImpressaoNaConta = 2
        )
        every { obterExtratoQuitacaoImovel(2082292, 2010) } returns ExtratoQuitacaoDTO(
            id = 11,
            anoReferencia = 2010
        )
    }

    private val extratoQuitacaoService = ExtratoQuitacaoService(repository)

    @Test
    fun `dado um imovel com indicador 2, retorna mensagem`(){
        val mensagem = extratoQuitacaoService.obterMensagemExtratoQuitacaoImovel(2096498, 201101)

        assertEquals("Em cumprimento a lei 12.007/2009, declaramos quitados os debitos de consumo de agua e/ou esgoto do ano de 2010.", mensagem)
    }

    @Test
    fun `dado um imovel com indicador null, retorna uma string vazia`(){
        val mensagem = extratoQuitacaoService.obterMensagemExtratoQuitacaoImovel(2082292, 201101)

        assertEquals("", mensagem)
    }
}