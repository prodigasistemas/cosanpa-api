package br.gov.pa.cosanpa.api.repository.faturamento.atividade

import br.gov.pa.cosanpa.api.dominio.faturamento.atividade.FaturamentoAtividadeCronograma
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.time.LocalDate
import java.time.LocalDateTime

interface FaturamentoAtividadeRepository : JpaRepository<FaturamentoAtividadeCronograma, Int> {

    @Query(
        value = " SELECT fac.dataPrevista FROM FaturamentoAtividadeCronograma fac  " +
                " INNER JOIN fac.faturamentoAtividade fa  " +
                " INNER JOIN fac.faturamentoCronogramaGrupoMensal fcm  " +
                " INNER JOIN fcm.faturamentoGrupo fg  " +
                " WHERE fg.id = :idFaturamentoGrupo " +
                " AND fa.id = :idFaturamentoAtividade " +
                " AND fcm.anoMesReferencia = :anoMesReferencia"
    )
    fun obterDataPrevista(idFaturamentoGrupo: Int, idFaturamentoAtividade: Int, anoMesReferencia: Int): LocalDate

    @Query(
        value = " SELECT fac.dataRealizacao FROM FaturamentoAtividadeCronograma fac  " +
                " INNER JOIN fac.faturamentoAtividade fa  " +
                " INNER JOIN fac.faturamentoCronogramaGrupoMensal fcm  " +
                " INNER JOIN fcm.faturamentoGrupo fg  " +
                " WHERE fg.id = :idFaturamentoGrupo " +
                " AND fa.id = :idFaturamentoAtividade " +
                " AND fcm.anoMesReferencia = :anoMesReferencia"
    )
    fun obterDataRealizacao(idFaturamentoGrupo: Int, idFaturamentoAtividade: Int, anoMesReferencia: Int): LocalDate?

    @Query(
        value = " SELECT fac.dataComando FROM FaturamentoAtividadeCronograma fac  " +
                " INNER JOIN fac.faturamentoAtividade fa  " +
                " INNER JOIN fac.faturamentoCronogramaGrupoMensal fcm  " +
                " INNER JOIN fcm.faturamentoGrupo fg  " +
                " WHERE fg.id = :idFaturamentoGrupo " +
                " AND fa.id = :idFaturamentoAtividade " +
                " AND fcm.anoMesReferencia = :anoMesReferencia"
    )
    fun obterDataComando(idFaturamentoGrupo: Int, idFaturamentoAtividade: Int, anoMesReferencia: Int): LocalDateTime?
}