package br.gov.pa.cosanpa.api.business.faturamento.impressaosimultanea.dadosconsumotarifa

import br.gov.pa.cosanpa.api.business.faturamento.impressaosimultanea.DadosConsumoTarifaBO
import br.gov.pa.cosanpa.api.dominio.faturamento.consumo.ConsumoTarifaFaixa
import br.gov.pa.cosanpa.api.dto.Dto
import br.gov.pa.cosanpa.api.dto.faturamento.consumo.ConsumoTarifaCategoriaDTO
import br.gov.pa.cosanpa.api.dto.faturamento.consumo.ConsumoTarifaFaixaDTO
import br.gov.pa.cosanpa.api.dto.faturamento.consumo.ConsumoTarifaVigenciaDTO
import br.gov.pa.cosanpa.api.service.cadastro.SistemaParametrosService
import br.gov.pa.cosanpa.api.service.cadastro.imovel.CategoriaService
import br.gov.pa.cosanpa.api.service.cadastro.imovel.ImovelService
import br.gov.pa.cosanpa.api.service.faturamento.atividade.FaturamentoAtividadeService
import br.gov.pa.cosanpa.api.service.faturamento.consumo.ConsumoTarifaService
import br.gov.pa.cosanpa.api.testobjects.sistemaParametros
import br.gov.pa.cosanpa.api.view.IView
import br.gov.pa.cosanpa.api.view.View
import br.gov.pa.cosanpa.api.view.faturamento.consumo.ConsumoTarifaCategoriaView
import br.gov.pa.cosanpa.api.view.faturamento.consumo.ConsumoTarifaFaixaView
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.math.BigDecimal
import java.math.RoundingMode
import java.time.LocalDate

class DadosConsumoTarifaBOTest {
    private val sistemaParametrosService: SistemaParametrosService = mockk {
        every { obterParametrosDoSistema() } returns sistemaParametros
    }

    private val consumoTarifaService: ConsumoTarifaService = consumoTarifaServiceMock

    private val faturamentoAtividadeService: FaturamentoAtividadeService = mockk {
        every { obterDataPrevista(any(), any(), any()) } returns LocalDate.now().minusDays(1)
    }
    private val categoriaService: CategoriaService = mockk {
        every { obterColecaoIdDescricaoCategorias() } returns listOf(
            Dto(id = 1, descricao = "RESIDENCIAL", nome = null, codigo = null),
            Dto(id = 2, descricao = "COMERCIAL", nome = null, codigo = null),
            Dto(id = 3, descricao = "INDUSTRIAL", nome = null, codigo = null),
            Dto(id = 4, descricao = "PUBLICO", nome = null, codigo = null)
        )

        every { obterCategoriaViewSimples(Dto(id = 1, descricao = "RESIDENCIAL", nome = null, codigo = null)) } returns
                View(id = 1, descricao = "RESIDENCIAL")

        every { obterCategoriaViewSimples(Dto(id = 2, descricao = "COMERCIAL", nome = null, codigo = null)) } returns
                View(id = 2, descricao = "COMERCIAL")

        every { obterCategoriaViewSimples(Dto(id = 3, descricao = "INDUSTRIAL", nome = null, codigo = null)) } returns
                View(id = 3, descricao = "INDUSTRIAL")

        every { obterCategoriaViewSimples(Dto(id = 4, descricao = "PUBLICO", nome = null, codigo = null)) } returns
                View(id = 4, descricao = "PUBLICO")


    }
    private val imovelService: ImovelService = mockk {
        every { obterColecaoIdConsumoTarifaEmUso() } returns listOf(1)
    }

    private val dadosConsumoTarifaBO = DadosConsumoTarifaBO(
        sistemaParametrosService,
        consumoTarifaService,
        faturamentoAtividadeService,
        categoriaService,
        imovelService
    )

    @Test
    fun `dada a chamada do metodo com grupo passado por parametro, obtem um mapa dos consumos categorias`() {
        val mapCategoria= mutableMapOf<String, Any>()
        val mapTarifas = mutableMapOf<String, Any>()
        val mapConsumoTarifaCategoria = mutableMapOf<String, Any>()

        mapConsumoTarifaCategoria["consumoTarifaCategoria"] = ConsumoTarifaCategoriaView(
            id = 150,
            numeroConsumoMinimo = 10,
            valorTarifaMinima = BigDecimal(39.10).setScale(2, RoundingMode.HALF_UP)
        )

        mapConsumoTarifaCategoria["consumoTarifaFaixa"] = listOf(
            ConsumoTarifaFaixaView(
                id = 230,
                numeroConsumoFaixaInicio = 11,
                numeroConsumoFaixaFim = 20,
                valorConsumoTarifa = BigDecimal(5.58).setScale(2, RoundingMode.HALF_UP)
            ), ConsumoTarifaFaixaView(
                id = 231,
                numeroConsumoFaixaInicio = 21,
                numeroConsumoFaixaFim = 30,
                valorConsumoTarifa = BigDecimal(7.47).setScale(2, RoundingMode.HALF_UP)
            ), ConsumoTarifaFaixaView(
                id = 232,
                numeroConsumoFaixaInicio = 31,
                numeroConsumoFaixaFim = 40,
                valorConsumoTarifa = BigDecimal(8.42).setScale(2, RoundingMode.HALF_UP)
            ), ConsumoTarifaFaixaView(
                id = 233,
                numeroConsumoFaixaInicio = 41,
                numeroConsumoFaixaFim = 50,
                valorConsumoTarifa = BigDecimal(11.67).setScale(2, RoundingMode.HALF_UP)
            ), ConsumoTarifaFaixaView(
                id = 234,
                numeroConsumoFaixaInicio = 51,
                numeroConsumoFaixaFim = 999999,
                valorConsumoTarifa = BigDecimal(15.16).setScale(2, RoundingMode.HALF_UP)
            )
        )
        mapTarifas["TARIFA NORMAL"] = listOf(mapConsumoTarifaCategoria)
        mapCategoria["RESIDENCIAL"] = listOf(mapTarifas)

        val dadosConsumoTarifa = dadosConsumoTarifaBO.obter(201)
        assertEquals(mapCategoria["RESIDENCIAL"], dadosConsumoTarifa["RESIDENCIAL"])
    }
}