package br.gov.pa.cosanpa.api.repository.faturamento.consumo

import br.gov.pa.cosanpa.api.dominio.faturamento.consumo.ConsumoTarifa
import br.gov.pa.cosanpa.api.dto.faturamento.consumo.ConsumoTarifaCategoriaDTO
import br.gov.pa.cosanpa.api.dto.faturamento.consumo.ConsumoTarifaFaixaDTO
import br.gov.pa.cosanpa.api.dto.faturamento.consumo.ConsumoTarifaVigenciaDTO
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
    fun obterConsumoMinimoTarifaCategoria(idConsumoTarifaVigencia: Int?, idCategoria: Int?): Int

    @Query(
        value = " SELECT new br.gov.pa.cosanpa.api.dto.faturamento.consumo.ConsumoTarifaCategoriaDTO( " +
                " ctcg.id as id, " +
                " ct.id as idConsumoTarifa, " +
                " ctv.dataVigencia as dataVigenciaConsumoTarifaVigencia, " +
                " catg.id as idCategoria," +
                " ctcg.numeroConsumoMinimo as numeroConsumoMinimo, " +
                " ctcg.valorTarifaMinima as valorTarifaMinima)" +
                " FROM ConsumoTarifaCategoria ctcg " +
                " INNER JOIN ctcg.consumoTarifaVigencia ctv " +
                " INNER JOIN ctv.consumoTarifa ct " +
                " INNER JOIN ctcg.categoria catg " +
                " where ctv.dataVigencia = :dataVigencia " +
                " AND ct.id = :idConsumoTarifa " +
                " AND catg.id = :idCategoria " +
                " ORDER BY ctv.dataVigencia DESC"
    )
    fun obterDadosConsumoTarifaPorDataVigencia(
        dataVigencia: LocalDate,
        idConsumoTarifa: Int,
        idCategoria: Int,
    ) : ConsumoTarifaCategoriaDTO

    @Query(
        value = " SELECT new br.gov.pa.cosanpa.api.dto.faturamento.consumo.ConsumoTarifaCategoriaDTO( " +
                " ctcg.id as id, " +
                " ct.id as idConsumoTarifa, " +
                " ctv.dataVigencia as dataVigenciaConsumoTarifaVigencia, " +
                " catg.id as idCategoria," +
                " ctcg.numeroConsumoMinimo as numeroConsumoMinimo, " +
                " ctcg.valorTarifaMinima as valorTarifaMinima)" +
                " FROM ConsumoTarifaCategoria ctcg " +
                " INNER JOIN ctcg.consumoTarifaVigencia ctv " +
                " INNER JOIN ctv.consumoTarifa ct " +
                " INNER JOIN ctcg.categoria catg " +
                " where ctv.dataVigencia between :dataLeituraAnterior  and  :dataLeituraAtual " +
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
                " ct.id as idConsumoTarifa, " +
                " ctv.dataVigencia as dataVigenciaConsumoTarifaVigencia, " +
                " catg.id as idCategoria, " +
                " ctfx.numeroConsumoFaixaInicio as numeroConsumoFaixaInicio, " +
                " ctfx.numeroConsumoFaixaFim as numeroConsumoFaixaFim, " +
                " ctfx.valorConsumoTarifa as valorConsumoTarifa) " +
                " FROM ConsumoTarifaFaixa ctfx " +
                " INNER JOIN ctfx.consumoTarifaCategoria ctcg " +
                " INNER JOIN ctcg.consumoTarifaVigencia ctv " +
                " INNER JOIN ctv.consumoTarifa ct " +
                " INNER JOIN ctcg.categoria catg " +
                " WHERE ctcg.id IN (:idsConsumoTarifaCategoria) " +
                " ORDER BY ct.id,ctv.dataVigencia, catg.id, ctfx.numeroConsumoFaixaInicio "
    )
    fun obterDados(idsConsumoTarifaCategoria: List<Int>) : List<ConsumoTarifaFaixaDTO>

    @Query(
        value = "SELECT  new br.gov.pa.cosanpa.api.dto.faturamento.consumo.ConsumoTarifaVigenciaDTO( " +
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
    fun obterDataVigente(idConsumoTarifa: Int, dataCorrente: LocalDate): ConsumoTarifaVigenciaDTO
}
