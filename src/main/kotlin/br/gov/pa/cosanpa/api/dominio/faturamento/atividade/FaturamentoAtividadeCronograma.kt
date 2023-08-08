package br.gov.pa.cosanpa.api.dominio.faturamento.atividade

import br.gov.pa.cosanpa.api.dominio.faturamento.grupo.FaturamentoGrupoCronogramaMensal
import jakarta.persistence.*
import java.time.LocalDate
import java.time.LocalDateTime

@Entity
@Table(name = "fatur_ativ_cronograma", schema = "faturamento")
data class FaturamentoAtividadeCronograma(
    @Id
    @Column(name = "ftac_id")
    val id: Int,
    @Column(name = "ftac_dtprevista")
    val dataPrevista: LocalDate?,
    @Column(name = "ftac_tmrealizacao")
    val dataRealizacao: LocalDateTime?,
    @Column(name = "ftac_tmcomando")
    val dataComando: LocalDateTime?,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ftat_id")
    val faturamentoAtividade: FaturamentoAtividade,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ftcm_id")
    val faturamentoCronogramaGrupoMensal: FaturamentoGrupoCronogramaMensal
)
