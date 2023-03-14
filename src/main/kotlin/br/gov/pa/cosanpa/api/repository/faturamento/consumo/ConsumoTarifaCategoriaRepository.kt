package br.gov.pa.cosanpa.api.repository.faturamento.consumo

import br.gov.pa.cosanpa.api.dominio.faturamento.consumo.ConsumoTarifaCategoria
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

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
}