package br.gov.pa.cosanpa.api.business.micromedicao.consumo

import br.gov.pa.cosanpa.api.dominio.cadastro.SistemaParametros
import br.gov.pa.cosanpa.api.dto.micromedicao.consumo.ConsumoHistoricoDTO
import br.gov.pa.cosanpa.api.service.cadastro.SistemaParametrosService
import br.gov.pa.cosanpa.api.service.micromedicao.consumo.ConsumoHistoricoService
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class ObterMediaConsumoAguaEsgotoTest {

    private val sistemaParametros: SistemaParametrosService = mockk {
        every { retornaParametrosDoSistema() } returns SistemaParametros(1, 6)
    }

    private val consumoHistoricoService: ConsumoHistoricoService = mockk {
        every { obterListaConsumos(any(), any(), any(), any()) } returns listOf(
            ConsumoHistoricoDTO(referencia = 202211, numeroCalculoConsumoMedia = 10),
            ConsumoHistoricoDTO(referencia = 202210, numeroCalculoConsumoMedia = 20),
            ConsumoHistoricoDTO(referencia = 202209, numeroCalculoConsumoMedia = 30),
            ConsumoHistoricoDTO(referencia = 202208, numeroCalculoConsumoMedia = 10),
            ConsumoHistoricoDTO(referencia = 202207, numeroCalculoConsumoMedia = 10),
            ConsumoHistoricoDTO(referencia = 202206, numeroCalculoConsumoMedia = 10)
        )
    }

    private val obterMediaConsumoAguaEsgoto = ObterMediaConsumoAguaEsgoto(sistemaParametros, consumoHistoricoService)
    @Test
    fun `dados os parametros, retornar a media de consumo do imovel`(){
        val media = obterMediaConsumoAguaEsgoto.obter(7389353, 202212, 1)

        assertEquals(15, media)
    }

}