package br.gov.pa.cosanpa.api.repository.faturamento

import br.gov.pa.cosanpa.api.dominio.faturamento.FaturamentoAtividadeCronograma
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.time.LocalDateTime

interface FaturamentoAtividadeCronogramaRepository : JpaRepository<FaturamentoAtividadeCronograma, Int> {

    @Query(
        value = " SELECT fac.dataPrevista FROM FaturamentoAtividadeCronograma fac  " +
                " INNER JOIN fac.faturamentoAtividade fa  " +
                " INNER JOIN fac.faturamentoCronogramaGrupoMensal fcm  " +
                " INNER JOIN fcm.faturamentoGrupo fg  " +
                " WHERE fg.id = :idFaturamentoGrupo " +
                " AND fa.id = :idFaturamentoAtividade " +
                " AND fcm.anoMesReferencia = :anoMesReferencia"
    )
    fun obterDataPrevista(idFaturamentoGrupo: Int, idFaturamentoAtividade: Int, anoMesReferencia: Int) : LocalDateTime
}