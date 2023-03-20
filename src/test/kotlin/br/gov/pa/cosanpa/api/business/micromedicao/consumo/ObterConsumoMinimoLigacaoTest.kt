package br.gov.pa.cosanpa.api.business.micromedicao.consumo

import br.gov.pa.cosanpa.api.dto.cadastro.imovel.CategoriaDTO
import br.gov.pa.cosanpa.api.dto.cadastro.imovel.ImovelDTO
import br.gov.pa.cosanpa.api.dto.faturamento.consumo.ConsumoTarifaVigenciaDTO
import br.gov.pa.cosanpa.api.service.cadastro.imovel.ImovelService
import br.gov.pa.cosanpa.api.service.faturamento.consumo.ConsumoTarifaCategoriaService
import br.gov.pa.cosanpa.api.service.faturamento.consumo.ConsumoTarifaVigenciaService
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.util.*

class ObterConsumoMinimoLigacaoTest {

    private val imovelService: ImovelService = mockk {
        every { obterConsumoTarifaImovel(any()) } returns ImovelDTO(id = 7389353, consumoTarifa = 1)
        every { obterDadosCategoriasPorImovel(any()) } returns listOf(CategoriaDTO(id = 1, descricao = "RESIDENCIAL", quantidadeEconomias = 3))
    }
    private val consumoTarifaVigenciaService: ConsumoTarifaVigenciaService = mockk {
        every { obterTarifaVigenciaCorrente(any()) } returns ConsumoTarifaVigenciaDTO(id = 89, Date())
    }

    private val consumoTarifaCategoriaService: ConsumoTarifaCategoriaService = mockk {
        every { obterNumeroConsumoMinimoTarifaCategoria(any(), any()) } returns 10
    }

    private val obterConsumoMinimoLigacao = ConsumoMinimoLigacaoBO(
        imovelService = imovelService,
        consumoTarifaVigenciaService = consumoTarifaVigenciaService,
        consumoTarifaCategoriaService = consumoTarifaCategoriaService
    )

    @Test
    fun `dado os parametros, retorna o consumo minimo por economia`() {
        val minimo = obterConsumoMinimoLigacao.obter(7389353)
        assertEquals(30, minimo)
    }

}