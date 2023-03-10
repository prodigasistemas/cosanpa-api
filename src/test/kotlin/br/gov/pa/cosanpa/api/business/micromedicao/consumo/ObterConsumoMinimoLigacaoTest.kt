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

    val imovelService: ImovelService = mockk {
        every { obterConsumoTarifaImovel(any()) } returns ImovelDTO(id = 7389353, consumoTarifa = 1)
        every { obterCategorias(any()) } returns listOf(CategoriaDTO(1, "RESIDENCIAL", quantidadeEconomias = 3))
    }
    val consumoTarifaVigenciaService: ConsumoTarifaVigenciaService = mockk {
        every { obterTarifaVigenciaCorrente(any()) } returns ConsumoTarifaVigenciaDTO(id = 89, Date())
    }

    val consumoTarifaCategoriaService: ConsumoTarifaCategoriaService = mockk {
        every { obterNumeroConsumoMinimoTarifaCategoria(any(), any()) } returns 10
    }

    val obterConsumoMinimoLigacao = ObterConsumoMinimoLigacao(
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