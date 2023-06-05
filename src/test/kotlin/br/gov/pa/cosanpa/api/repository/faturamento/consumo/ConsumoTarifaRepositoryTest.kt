package br.gov.pa.cosanpa.api.repository.faturamento.consumo

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.TestPropertySource
import java.time.LocalDate

@DataJpaTest
@TestPropertySource(locations = ["/application.yml"])
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ConsumoTarifaRepositoryTest {

    @Autowired
    private lateinit var repo: ConsumoTarifaRepository

    @Test
    fun `dado um id de tarifa vigencia e categoria, entao retorna numero consumo minimo`() {
        val numeroConsumoMinimo = repo.obterConsumoMinimoTarifaCategoria(1, 1)
        assertNotNull(numeroConsumoMinimo)
    }

    @Test
    fun `dado os parametros corretos, entao retorna dados de ConsumoTarifa`(){
        val tarifaCategoriaDTO = repo.obterDadosConsumoTarifaCategoriaPorDataVigencia(
            LocalDate.of(2022,11,28),
            1,
            1
        )
        assertNotNull(tarifaCategoriaDTO)
        println(tarifaCategoriaDTO)
    }

    @Test
    fun `dado os parametros corretos, entao retorna dados de ConsumoTarifa Proporcional`(){
        val tarifaCategoriaDTO = repo.obterDadosConsumoTarifaProporcional(
            LocalDate.now().minusYears(3),
            LocalDate.now(),
            1,
            1
        )
        assertNotNull(tarifaCategoriaDTO)
        println(tarifaCategoriaDTO)
    }

    @Test
    fun `dado uma lista de ids de ConsumoTarifaCategoria, entao retorna dados referentes a ConsumoTarifaFaixa`(){
        val tarifaFaixaDTOList = repo.obterDadosConsumoTarifaFaixaPorCategoria(1)
        assertNotNull(tarifaFaixaDTOList)
        println(tarifaFaixaDTOList)
    }

    @Test
    fun `dado um id vigencia e uma data atual, retorna lista de dtos com vigencia mais atual`() {
        val date = repo.obterDataConsumoTarifaVigente(1, LocalDate.now())
        assertNotNull(date)
        println(date)
    }

    @Test
    fun `dado um id de consumo tarifa, retorna dtos de dados`() {
        val dto = repo.obterDadosConsumoTarifa(1)
        assertNotNull(dto)
        assertEquals("TARIFA NORMAL", dto.descricao)
    }

    @Test
    fun `dado a chamada da consulta, entao retorna dtos de dados de taria ipo calculo em uso`() {
        val dtos = repo.obterColecaoTarifaTipoCaluloEmUso()
        assertNotNull(dtos)
        assertFalse(dtos.isEmpty())
        println(dtos)
    }
}