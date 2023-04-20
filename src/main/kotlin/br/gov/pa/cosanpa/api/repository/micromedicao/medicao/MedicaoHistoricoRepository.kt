package br.gov.pa.cosanpa.api.repository.micromedicao.medicao

import br.gov.pa.cosanpa.api.dominio.micromedicao.medicao.MedicaoHistorico
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.time.LocalDate

interface MedicaoHistoricoRepository : JpaRepository<MedicaoHistorico, Int> {

    @Query(
        value = " SELECT medhist.dataLeituraAnteriorFaturamento " +
                " FROM MedicaoHistorico medhist " +
                " INNER JOIN medhist.medicaoTipo medtipo " +
                " LEFT JOIN medhist.ligacaoAgua lagu " +
                " LEFT JOIN medhist.imovel imovel " +
                " WHERE imovel.id = :idImovel " +
                " ORDER BY medhist.id DESC " +
                " LIMIT 1 "
    )
    fun obterDataLeituraAnteriorPorImovelId(idImovel: Int) : LocalDate?
}