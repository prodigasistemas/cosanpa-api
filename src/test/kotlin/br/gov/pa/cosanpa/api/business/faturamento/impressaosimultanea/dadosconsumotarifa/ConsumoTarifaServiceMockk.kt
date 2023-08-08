package br.gov.pa.cosanpa.api.business.faturamento.impressaosimultanea.dadosconsumotarifa

import br.gov.pa.cosanpa.api.dto.faturamento.consumo.ConsumoTarifaCategoriaDTO
import br.gov.pa.cosanpa.api.dto.faturamento.consumo.ConsumoTarifaFaixaDTO
import br.gov.pa.cosanpa.api.dto.faturamento.consumo.ConsumoTarifaVigenciaDTO
import br.gov.pa.cosanpa.api.service.faturamento.consumo.ConsumoTarifaService
import br.gov.pa.cosanpa.api.view.IView
import br.gov.pa.cosanpa.api.view.faturamento.consumo.ConsumoTarifaCategoriaView
import br.gov.pa.cosanpa.api.view.faturamento.consumo.ConsumoTarifaFaixaView
import io.mockk.every
import io.mockk.mockk
import java.math.BigDecimal
import java.math.RoundingMode
import java.time.LocalDate

val consumoTarifaServiceMock: ConsumoTarifaService = mockk {

    every { obterDadosConsumoTarifaView(any()) } returns object : IView {
        override val id = 1
        override val descricao = "TARIFA NORMAL"
    }

    every { obterDataTarifaVigenciaCorrente(1) } returns ConsumoTarifaVigenciaDTO(
        id = 1,
        dataVigencia = LocalDate.of(2022, 11, 28)
    )

    every { obterDadosConsumoTarifaCategoriaPorDataVingente(any(), any(), any()) } returns listOf()

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
    } returns listOf(
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

    every {
        obterConsumoTarifaFaixaView(151)
    } returns listOf(
        ConsumoTarifaFaixaView(
            id = 235,
            numeroConsumoFaixaInicio = 11,
            numeroConsumoFaixaFim = 999999,
            valorConsumoTarifa = BigDecimal(14.56).setScale(2, RoundingMode.HALF_UP)
        )
    )

    every {
        obterConsumoTarifaFaixaView(152)
    } returns listOf(
        ConsumoTarifaFaixaView(
            id = 236,
            numeroConsumoFaixaInicio = 11,
            numeroConsumoFaixaFim = 999999,
            valorConsumoTarifa = BigDecimal(18.63).setScale(2, RoundingMode.HALF_UP)
        )
    )

    every {
        obterConsumoTarifaFaixaView(153)
    } returns listOf(
        ConsumoTarifaFaixaView(
            id = 237,
            numeroConsumoFaixaInicio = 11,
            numeroConsumoFaixaFim = 999999,
            valorConsumoTarifa = BigDecimal(14.56).setScale(2, RoundingMode.HALF_UP)
        )
    )
}
