package br.gov.pa.cosanpa.api.repository.faturamento.consumo

import br.gov.pa.cosanpa.api.dominio.faturamento.consumo.ConsumoTarifaVigencia
import br.gov.pa.cosanpa.api.dto.faturamento.consumo.ConsumoTarifaVigenciaDTO
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.util.Date

interface ConsumoTarifaVigenciaRepository : JpaRepository<ConsumoTarifaVigencia, Int> {

    @Query(
        value = "SELECT  new br.gov.pa.cosanpa.api.dto.faturamento.consumo"
                + ".ConsumoTarifaVigenciaDTO(ctv.id as id, ctv.dataVigencia as dataVigencia) "
                + "FROM ConsumoTarifaVigencia ctv  "
                + "INNER JOIN ctv.consumoTarifa ct "
                + "WHERE ct.id = :idConsumoTarifa "
                + "and ctv.dataVigencia in "
                + "(SELECT  max(ctv2.dataVigencia) FROM ConsumoTarifaVigencia ctv2 "
                + "INNER JOIN ctv2.consumoTarifa ct2 "
                + "WHERE ct2.id = :idConsumoTarifa and ctv2.dataVigencia  <= :dataCorrente)"
    )
    fun obterConsumoTarifaVigencia(idConsumoTarifa: Int?, dataCorrente: Date): ConsumoTarifaVigenciaDTO
}