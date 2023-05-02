package br.gov.pa.cosanpa.api.repository.faturamento.consumo

import br.gov.pa.cosanpa.api.dominio.faturamento.consumo.ConsumoTarifaFaixa
import br.gov.pa.cosanpa.api.dto.faturamento.consumo.ConsumoTarifaFaixaDTO
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface ConsumoTarifaFaixaRepository : JpaRepository<ConsumoTarifaFaixa, Int> {

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
}