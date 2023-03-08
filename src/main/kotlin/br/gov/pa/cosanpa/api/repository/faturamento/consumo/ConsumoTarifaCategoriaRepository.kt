package br.gov.pa.cosanpa.api.repository.faturamento.consumo

import br.gov.pa.cosanpa.api.dominio.faturamento.consumo.ConsumoTarifaCategoria
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface ConsumoTarifaCategoriaRepository : JpaRepository<ConsumoTarifaCategoria, Int> {

    @Query(
        value = "select ct.numeroConsumoMinimo " +
                " from ConsumoTarifaCategoria ct " +
                " inner join ct.consumoTarifaVigencia.consumoTarifa " +
                " inner join ct.categoria " +
                " where ct.consumoTarifaVigencia.id = :idConsumoTarifaVigencia " +
                " and ct.categoria.id = :idCategoria"
    )
    fun obterConsumoMinimoTarifaCategoria(idConsumoTarifaVigencia: Int?, idCategoria: Int?): Int
}