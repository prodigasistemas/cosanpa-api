package br.gov.pa.cosanpa.api.repository.faturamento.consumo

import br.gov.pa.cosanpa.api.dominio.faturamento.consumo.ConsumoTarifaVigencia
import br.gov.pa.cosanpa.api.dto.faturamento.consumo.ConsumoTarifaVigenciaDTO
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.util.Date

interface ConsumoTarifaVigenciaRepository : JpaRepository<ConsumoTarifaVigencia, Int> {

    @Query(
        value = "select new br.gov.pa.cosanpa.api.dto.faturamento.consumo"
                + ".ConsumoTarifaVigenciaDTO(ctv.id as id, ctv.dataVigencia as dataVigencia) "
                + "from ConsumoTarifaVigencia ctv  "
                + "inner join ctv.consumoTarifa ct "
                + "where ct.id = :idConsumoTarifa "
                + "and ctv.dataVigencia in "
                + "(select max(ctv2.dataVigencia) from ConsumoTarifaVigencia ctv2 "
                + "inner join ctv2.consumoTarifa ct2 "
                + "where ct2.id = :idConsumoTarifa and ctv2.dataVigencia  <= :dataCorrente)"
    )
    fun obterConsumoTarifaVigencia(idConsumoTarifa: Int?, dataCorrente: Date): ConsumoTarifaVigenciaDTO
}