package br.gov.pa.cosanpa.api.repository.faturamento.consumo

import br.gov.pa.cosanpa.api.dominio.faturamento.consumo.ConsumoTarifaCategoria
import br.gov.pa.cosanpa.api.dto.faturamento.consumo.ConsumoTarifaCategoriaDTO
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.time.LocalDate

interface ConsumoTarifaCategoriaRepository : JpaRepository<ConsumoTarifaCategoria, Int> {

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
}