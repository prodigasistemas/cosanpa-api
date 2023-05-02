package br.gov.pa.cosanpa.api.repository.micromedicao.medicao

import br.gov.pa.cosanpa.api.dominio.micromedicao.medicao.MedicaoHistorico
import br.gov.pa.cosanpa.api.dto.micromedicao.medicao.MedicaoHistoricoDTO
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.time.LocalDate

interface MedicaoHistoricoRepository : JpaRepository<MedicaoHistorico, Int> {

    @Query(
        value = " SELECT medhist.dataLeituraAtualFaturamento " +
                " FROM MedicaoHistorico medhist " +
                " INNER JOIN medhist.medicaoTipo medtipo " +
                " LEFT JOIN medhist.ligacaoAgua lagu " +
                " LEFT JOIN medhist.imovel imovel " +
                " WHERE ((imovel.id = :idImovel AND lagu.id = :idImovel) OR (lagu.id = :idImovel)) " +
                " AND medhist.anoMesReferencia = :anoMesReferencia " +
                " ORDER BY medhist.id DESC " +
                " LIMIT 1 "
    )
    fun obterMaiorDataLeituraAtualPorImovelId(idImovel: Int?, anoMesReferencia: Int) : LocalDate?

    @Query(
        value = " SELECT new br.gov.pa.cosanpa.api.dto.micromedicao.medicao.MedicaoHistoricoDTO( " +
                " mdhi.leituraAtualFaturamento as leituraAtualFaturamento, " +
                " mdhi.dataLeituraAtualFaturamento as dataLeituraAtualFaturamento, " +
                " ltst.id as idLeituraSituacaoAtual, " +
                " mdhi.consumoMedioHidrometro as consumoMedioHidrometro, " +
                " mdhi.leituraAtualInformada as leituraAtualInformada, " +
                " mdhi.dataLeituraAtualInformada as dataLeituraAtualInformada, " +
                " mdhi.dataLeituraAnteriorFaturamento as dataLeituraAnteriorFaturamento, " +
                " mdhi.leituraAnteriorInformada as leituraAnteriorInformada, " +
                " mdhi.leituraAnterioFaturamento as leituraAnterioFaturamento, " +
                " mdhi.medicaoTipo.id as idMedicaoTipo) " +
                " FROM MedicaoHistorico mdhi " +
                " LEFT JOIN mdhi.ligacaoAgua lagu " +
                " LEFT JOIN mdhi.imovel imovel " +
                " LEFT JOIN mdhi.leituraSituacaoAtual ltst " +
                " WHERE mdhi.anoMesReferencia = :anoMesReferencia " +
                " AND ((imovel.id = :idImovel AND lagu.id = :idImovel) OR (lagu.id = :idImovel)) "
    )
    fun obterDadosMedicaoHistoricoPorImovelId(
        anoMesReferencia: Int,
        idImovel: Int?
    ) : MedicaoHistoricoDTO?
}