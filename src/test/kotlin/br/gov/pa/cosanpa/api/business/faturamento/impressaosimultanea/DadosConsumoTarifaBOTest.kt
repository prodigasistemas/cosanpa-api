package br.gov.pa.cosanpa.api.business.faturamento.impressaosimultanea

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
import br.gov.pa.cosanpa.api.view.View
import br.gov.pa.cosanpa.api.view.faturamento.consumo.ConsumoTarifaCategoriaView
import br.gov.pa.cosanpa.api.view.faturamento.consumo.ConsumoTarifaFaixaView
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Test
import java.math.BigDecimal
import java.math.RoundingMode
import java.time.LocalDate

class DadosConsumoTarifaBOTest {
    private val sistemaParametrosService: SistemaParametrosService = mockk {
        every { obterParametrosDoSistema() } returns sistemaParametros
    }

    private val consumoTarifaService: ConsumoTarifaService = mockk {

        every { obterDataTarifaVigenciaCorrente(1) } returns ConsumoTarifaVigenciaDTO(
            id = 1,
            dataVigencia = LocalDate.of(2022,11,28)
        )

        every { obterDadosConsumoTarifaCategoriaProporcional(any(), 1, 1) } returns listOf(
            ConsumoTarifaCategoriaDTO(
                id = 150,
                numeroConsumoMinimo = 10,
                valorTarifaMinima = BigDecimal(39.10).setScale(2, RoundingMode.HALF_UP),
                idConsumoTarifaVigencia = 89,
                idCategoria = 1
            )
        )

        every { obterDadosConsumoTarifaCategoriaProporcional(any(), 2, 1) } returns listOf(
            ConsumoTarifaCategoriaDTO(
                id = 151,
                numeroConsumoMinimo = 10,
                valorTarifaMinima = BigDecimal(116.70).setScale(2, RoundingMode.HALF_UP),
                idConsumoTarifaVigencia = 89,
                idCategoria = 2
            )
        )
        every { obterDadosConsumoTarifaCategoriaProporcional(any(), 3, 1) } returns listOf(
            ConsumoTarifaCategoriaDTO(
                id = 152,
                numeroConsumoMinimo = 10,
                valorTarifaMinima = BigDecimal(145.60).setScale(2, RoundingMode.HALF_UP),
                idConsumoTarifaVigencia = 89,
                idCategoria = 3
            )
        )
        every { obterDadosConsumoTarifaCategoriaProporcional(any(), 4, 1) } returns listOf(
            ConsumoTarifaCategoriaDTO(
                id = 153,
                numeroConsumoMinimo = 10,
                valorTarifaMinima = BigDecimal(116.70).setScale(2, RoundingMode.HALF_UP),
                idConsumoTarifaVigencia = 89,
                idCategoria = 4
            )
        )

        every {
            obterConsumoTarifaCategoriaView(
                ConsumoTarifaCategoriaDTO(
                    id = 150,
                    numeroConsumoMinimo = 10,
                    valorTarifaMinima = BigDecimal(39.10).setScale(2, RoundingMode.HALF_UP),
                    idConsumoTarifaVigencia = 89,
                    idCategoria = 1
                )
            )
        } returns ConsumoTarifaCategoriaView(
            id = 150,
            numeroConsumoMinimo = 10,
            valorTarifaMinima = BigDecimal(39.10).setScale(2, RoundingMode.HALF_UP)
        )

        every {
            obterConsumoTarifaCategoriaView(
                ConsumoTarifaCategoriaDTO(
                    id = 151,
                    numeroConsumoMinimo = 10,
                    valorTarifaMinima = BigDecimal(116.70).setScale(2, RoundingMode.HALF_UP),
                    idConsumoTarifaVigencia = 89,
                    idCategoria = 2
                )
            )
        } returns ConsumoTarifaCategoriaView(
            id = 151,
            numeroConsumoMinimo = 10,
            valorTarifaMinima = BigDecimal(116.70).setScale(2, RoundingMode.HALF_UP)
        )

        every {
            obterConsumoTarifaCategoriaView(
                ConsumoTarifaCategoriaDTO(
                    id = 152,
                    numeroConsumoMinimo = 10,
                    valorTarifaMinima = BigDecimal(145.60).setScale(2, RoundingMode.HALF_UP),
                    idConsumoTarifaVigencia = 89,
                    idCategoria = 3
                )
            )
        } returns ConsumoTarifaCategoriaView(
            id = 152,
            numeroConsumoMinimo = 10,
            valorTarifaMinima = BigDecimal(145.60).setScale(2, RoundingMode.HALF_UP)
        )

        every {
            obterConsumoTarifaCategoriaView(
                ConsumoTarifaCategoriaDTO(
                    id = 153,
                    numeroConsumoMinimo = 10,
                    valorTarifaMinima = BigDecimal(116.70).setScale(2, RoundingMode.HALF_UP),
                    idConsumoTarifaVigencia = 89,
                    idCategoria = 4
                )
            )
        } returns ConsumoTarifaCategoriaView(
            id = 153,
            numeroConsumoMinimo = 10,
            valorTarifaMinima = BigDecimal(116.70).setScale(2, RoundingMode.HALF_UP)
        )

        every { obterColecaoConsumoTarifaFaixaPorCategoria(150) } returns listOf(
            ConsumoTarifaFaixaDTO(
                id = 230,
                numeroConsumoFaixaInicio = 11,
                numeroConsumoFaixaFim = 20,
                valorConsumoTarifa = BigDecimal(5.58).setScale(2, RoundingMode.HALF_UP)
            ), ConsumoTarifaFaixaDTO(
                id = 231,
                numeroConsumoFaixaInicio = 21,
                numeroConsumoFaixaFim = 30,
                valorConsumoTarifa = BigDecimal(7.47).setScale(2, RoundingMode.HALF_UP)
            ), ConsumoTarifaFaixaDTO(
                id = 232,
                numeroConsumoFaixaInicio = 31,
                numeroConsumoFaixaFim = 40,
                valorConsumoTarifa = BigDecimal(8.42).setScale(2, RoundingMode.HALF_UP)
            ), ConsumoTarifaFaixaDTO(
                id = 233,
                numeroConsumoFaixaInicio = 41,
                numeroConsumoFaixaFim = 50,
                valorConsumoTarifa = BigDecimal(11.67).setScale(2, RoundingMode.HALF_UP)
            ), ConsumoTarifaFaixaDTO(
                id = 234,
                numeroConsumoFaixaInicio = 51,
                numeroConsumoFaixaFim = 999999,
                valorConsumoTarifa = BigDecimal(15.16).setScale(2, RoundingMode.HALF_UP)
            )
        )

        every { obterColecaoConsumoTarifaFaixaPorCategoria(151) } returns listOf(
            ConsumoTarifaFaixaDTO(
                id = 235,
                numeroConsumoFaixaInicio = 11,
                numeroConsumoFaixaFim = 999999,
                valorConsumoTarifa = BigDecimal(14.56).setScale(2, RoundingMode.HALF_UP)
            )
        )

        every { obterColecaoConsumoTarifaFaixaPorCategoria(152) } returns listOf(
            ConsumoTarifaFaixaDTO(
                id = 236,
                numeroConsumoFaixaInicio = 11,
                numeroConsumoFaixaFim = 999999,
                valorConsumoTarifa = BigDecimal(18.63).setScale(2, RoundingMode.HALF_UP)
            )
        )

        every { obterColecaoConsumoTarifaFaixaPorCategoria(153) } returns listOf(
            ConsumoTarifaFaixaDTO(
                id = 237,
                numeroConsumoFaixaInicio = 11,
                numeroConsumoFaixaFim = 999999,
                valorConsumoTarifa = BigDecimal(14.56).setScale(2, RoundingMode.HALF_UP)
            )
        )

        every {
            obterConsumoTarifaFaixaView(150)
        } returns listOf(ConsumoTarifaFaixaView(
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
        ))

        every {
            obterConsumoTarifaFaixaView(151)
        } returns listOf(ConsumoTarifaFaixaView(
            id = 235,
            numeroConsumoFaixaInicio = 11,
            numeroConsumoFaixaFim = 999999,
            valorConsumoTarifa = BigDecimal(14.56).setScale(2, RoundingMode.HALF_UP)
        ))

        every {
            obterConsumoTarifaFaixaView(152)
        } returns listOf(ConsumoTarifaFaixaView(
            id = 236,
            numeroConsumoFaixaInicio = 11,
            numeroConsumoFaixaFim = 999999,
            valorConsumoTarifa = BigDecimal(18.63).setScale(2, RoundingMode.HALF_UP)
        ))

        every {
            obterConsumoTarifaFaixaView(153)
        } returns listOf(ConsumoTarifaFaixaView(
            id = 237,
            numeroConsumoFaixaInicio = 11,
            numeroConsumoFaixaFim = 999999,
            valorConsumoTarifa = BigDecimal(14.56).setScale(2, RoundingMode.HALF_UP)
        ))
    }

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
        println(dadosConsumoTarifaBO.obter(201))
    }
}