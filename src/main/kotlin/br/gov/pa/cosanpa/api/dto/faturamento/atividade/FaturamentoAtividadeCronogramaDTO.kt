package br.gov.pa.cosanpa.api.dto.faturamento.atividade

import java.time.LocalDate
import java.time.LocalDateTime

data class FaturamentoAtividadeCronogramaDTO(
    val id: Int? = null,
    val dataPrevista: LocalDate? = null,
    val dataRealizacao: LocalDateTime? = null,
    val dataComando: LocalDateTime? = null,
    val idFaturamentoAtividade: Int? = null ,
    val idFaturamentoCronogramaGrupoMensal: Int? = null
)
