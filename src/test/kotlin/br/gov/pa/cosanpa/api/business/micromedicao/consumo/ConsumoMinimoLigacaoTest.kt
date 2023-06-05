package br.gov.pa.cosanpa.api.business.micromedicao.consumo

import br.gov.pa.cosanpa.api.dto.cadastro.imovel.CategoriaDTO
import br.gov.pa.cosanpa.api.dto.faturamento.consumo.ConsumoTarifaVigenciaDTO
import br.gov.pa.cosanpa.api.service.cadastro.imovel.CategoriaService
import br.gov.pa.cosanpa.api.service.cadastro.imovel.ImovelService
import br.gov.pa.cosanpa.api.service.faturamento.consumo.ConsumoTarifaService
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.time.LocalDate

class ConsumoMinimoLigacaoTest {

    private val imovelService: ImovelService = mockk {
        every { obterConsumoTarifaImovel(any()) } returns 1
    }

    private val categoriaService: CategoriaService = mockk {
        every { obterDadosCategorias(any()) } returns listOf(CategoriaDTO(id = 1, descricao = "RESIDENCIAL", quantidadeEconomias = 3))
    }

    private val consumoTarifaService: ConsumoTarifaService = mockk {
        every { obterDataTarifaVigenciaCorrente(any()) } returns ConsumoTarifaVigenciaDTO(id = 89, LocalDate.now())
        every { obterNumeroConsumoMinimoTarifaCategoria(any(), any()) } returns 10
    }

    private val obterConsumoMinimoLigacao = ConsumoMinimoLigacaoBO(
        imovelService = imovelService,
        categoriaService = categoriaService,
        consumoTarifaService = consumoTarifaService
    )

    @Test
    fun `dado os parametros, retorna o consumo minimo por economia`() {
        val minimo = obterConsumoMinimoLigacao.obter(7389353)
        assertEquals(30, minimo)
    }

}