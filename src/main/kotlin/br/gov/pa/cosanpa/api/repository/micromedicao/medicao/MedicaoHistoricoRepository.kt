package br.gov.pa.cosanpa.api.repository.micromedicao.medicao

import br.gov.pa.cosanpa.api.dominio.micromedicao.medicao.MedicaoHistorico
import br.gov.pa.cosanpa.api.dto.Dto
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
                " mdhi.id as id, " +
                " mdhi.anoMesReferencia as anoMesReferencia, " +
                " mdhi.dataLeituraAnteriorFaturamento as dataLeituraAnteriorFaturamento, " +
                " mdhi.leituraAnteriorInformada as leituraAnteriorInformada, " +
                " mdhi.leituraAnterioFaturamento as leituraAnterioFaturamento, " +
                " mdhi.dataLeituraAtualInformada as dataLeituraAtualInformada, " +
                " mdhi.leituraAtualInformada as leituraAtualInformada, " +
                " mdhi.dataLeituraAtualFaturamento as dataLeituraAtualFaturamento, " +
                " mdhi.leituraAtualFaturamento as leituraAtualFaturamento, " +
                " mdhi.consumoMedioHidrometro as consumoMedioHidrometro, " +
                " imovel.id as idImovel, " +
                " lagu.id as idLigacaoAgua, " +
                " mdhi.medicaoTipo.id as idMedicaoTipo, " +
                " mdhi.leituraSituacaoAnterior.id as idLeituraSituacaoAnterior, " +
                " mdhi.leituraSituacaoAtual.id as idLeituraSituacaoAtual, " +
                " mdhi.leituraAnormalidadeFaturamento.id as idLeituraAnormalidadeFaturamento, " +
                " mdhi.leituraAnormalidadeInformada.id as idLeituraAnormalidadeInformada) " +
                " FROM MedicaoHistorico mdhi " +
                " LEFT JOIN mdhi.ligacaoAgua lagu " +
                " LEFT JOIN mdhi.imovel imovel " +
                " LEFT JOIN mdhi.leituraSituacaoAtual ltst " +
                " WHERE mdhi.anoMesReferencia = :anoMesReferencia " +
                " AND ((imovel.id = :idImovel AND lagu.id = :idImovel) OR (lagu.id = :idImovel)) "
    )
    fun obterDadosMedicaoHistoricoPorImovelId(
        anoMesReferencia: Int,
        idImovel: Int
    ) : MedicaoHistoricoDTO?

    @Query(
        value = " SELECT new br.gov.pa.cosanpa.api.dto.Dto( " +
                " mt.id as id, " +
                " mt.descricao as descricao) " +
                " FROM MedicaoTipo mt " +
                " WHERE mt.id = :idMedicaoTipo "
    )
    fun obterDadosMedicaoTipo(idMedicaoTipo: Int): Dto
}