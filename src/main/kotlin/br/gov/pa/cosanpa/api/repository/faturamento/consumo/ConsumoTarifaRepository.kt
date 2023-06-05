package br.gov.pa.cosanpa.api.repository.faturamento.consumo

import br.gov.pa.cosanpa.api.dominio.faturamento.consumo.ConsumoTarifa
import br.gov.pa.cosanpa.api.dto.Dto
import br.gov.pa.cosanpa.api.dto.faturamento.consumo.ConsumoTarifaCategoriaDTO
import br.gov.pa.cosanpa.api.dto.faturamento.consumo.ConsumoTarifaFaixaDTO
import br.gov.pa.cosanpa.api.dto.faturamento.consumo.ConsumoTarifaVigenciaDTO
import br.gov.pa.cosanpa.api.util.ConstantesSistema
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.time.LocalDate

interface ConsumoTarifaRepository : JpaRepository<ConsumoTarifa, Int> {

    @Query(
        value = "SELECT  ct.numeroConsumoMinimo " +
                " FROM ConsumoTarifaCategoria ct " +
                " INNER JOIN ct.consumoTarifaVigencia.consumoTarifa " +
                " INNER JOIN ct.categoria " +
                " WHERE ct.consumoTarifaVigencia.id = :idConsumoTarifaVigencia " +
                " and ct.categoria.id = :idCategoria"
    )
    fun obterConsumoMinimoTarifaCategoria(idConsumoTarifaVigencia: Int, idCategoria: Int): Int

    @Query(
        value = " SELECT new br.gov.pa.cosanpa.api.dto.faturamento.consumo.ConsumoTarifaCategoriaDTO( " +
                " ctcg.id as id," +
                " ctcg.numeroConsumoMinimo as numeroConsumoMinimo," +
                " ctcg.valorTarifaMinima as valorTarifaMinima," +
                " ctv.id as idConsumoTarifaVigencia, " +
                " catg.id as idCategoria)" +
                " FROM ConsumoTarifaCategoria ctcg " +
                " INNER JOIN ctcg.consumoTarifaVigencia ctv " +
                " INNER JOIN ctv.consumoTarifa ct " +
                " INNER JOIN ctcg.categoria catg " +
                " where ctv.dataVigencia = :dataVigencia " +
                " AND ct.id = :idConsumoTarifa " +
                " AND catg.id = :idCategoria " +
                " ORDER BY ctv.dataVigencia DESC"
    )
    fun obterDadosConsumoTarifaCategoriaPorDataVigencia(
        dataVigencia: LocalDate,
        idConsumoTarifa: Int,
        idCategoria: Int,
    ) : List<ConsumoTarifaCategoriaDTO>

    @Query(
        value = " SELECT new br.gov.pa.cosanpa.api.dto.faturamento.consumo.ConsumoTarifaCategoriaDTO( " +
                " ctcg.id as id," +
                " ctcg.numeroConsumoMinimo as numeroConsumoMinimo," +
                " ctcg.valorTarifaMinima as valorTarifaMinima," +
                " ctv.id as idConsumoTarifaVigencia, " +
                " catg.id as idCategoria)" +
                " FROM ConsumoTarifaCategoria ctcg " +
                " INNER JOIN ctcg.consumoTarifaVigencia ctv " +
                " INNER JOIN ctv.consumoTarifa ct " +
                " INNER JOIN ctcg.categoria catg " +
                " WHERE ctv.dataVigencia " +
                " BETWEEN :dataLeituraAnterior  AND  :dataLeituraAtual" +
                " AND ct.id = :idConsumoTarifa " +
                " AND catg.id = :idCategoria " +
                " ORDER BY ctv.dataVigencia DESC"
    )
    fun obterDadosConsumoTarifaProporcional(
        dataLeituraAnterior: LocalDate,
        dataLeituraAtual: LocalDate,
        idConsumoTarifa: Int,
        idCategoria: Int,
    ) : List<ConsumoTarifaCategoriaDTO>

    @Query(
        value = " SELECT new br.gov.pa.cosanpa.api.dto.faturamento.consumo.ConsumoTarifaFaixaDTO( " +
                " ctfx.id as id, " +
                " ctfx.numeroConsumoFaixaInicio as numeroConsumoFaixaInicio, " +
                " ctfx.numeroConsumoFaixaFim as numeroConsumoFaixaFim, " +
                " ctfx.valorConsumoTarifa as valorConsumoTarifa, " +
                " ctcg.id as idConsumoTarifaCategoria) " +
                " FROM ConsumoTarifaFaixa ctfx " +
                " INNER JOIN ctfx.consumoTarifaCategoria ctcg " +
                " INNER JOIN ctcg.consumoTarifaVigencia ctv " +
                " INNER JOIN ctv.consumoTarifa ct " +
                " INNER JOIN ctcg.categoria catg " +
                " WHERE ctcg.id = :idConsumoTarifaCategoria " +
                " ORDER BY ct.id,ctv.dataVigencia, catg.id, ctfx.numeroConsumoFaixaInicio "
    )
    fun obterDadosConsumoTarifaFaixaPorCategoria(idConsumoTarifaCategoria: Int) : List<ConsumoTarifaFaixaDTO>

    @Query(
        value = "SELECT new br.gov.pa.cosanpa.api.dto.faturamento.consumo.ConsumoTarifaVigenciaDTO( " +
                " ctv.id as id, " +
                " ctv.dataVigencia as dataVigencia) "
                + "FROM ConsumoTarifaVigencia ctv  "
                + "INNER JOIN ctv.consumoTarifa ct "
                + "WHERE ct.id = :idConsumoTarifa "
                + "and ctv.dataVigencia in "
                + "(SELECT  max(ctv2.dataVigencia) FROM ConsumoTarifaVigencia ctv2 "
                + "INNER JOIN ctv2.consumoTarifa ct2 "
                + "WHERE ct2.id = :idConsumoTarifa and ctv2.dataVigencia  <= :dataCorrente)"
    )
    fun obterDataConsumoTarifaVigente(idConsumoTarifa: Int, dataCorrente: LocalDate): ConsumoTarifaVigenciaDTO

    @Query(
        value = " SELECT new br.gov.pa.cosanpa.api.dto.Dto( " +
                " cstf.id as id, " +
                " cstf.descricao as descricao) " +
                " FROM ConsumoTarifa cstf " +
                " WHERE cstf.id = :idConsumoTarifa"
    )
    fun obterDadosConsumoTarifa(idConsumoTarifa: Int): Dto

    @Query(
        value = " SELECT new br.gov.pa.cosanpa.api.dto.Dto( " +
                " ttc.id as id, " +
                " ttc.descricao as descricao) " +
                " FROM TarifaTipoCalculo ttc " +
                " WHERE ttc.indicadorUso = ${ConstantesSistema.INDICADOR_USO_ATIVO}"
    )
    fun obterColecaoTarifaTipoCaluloEmUso(): List<Dto>
}
