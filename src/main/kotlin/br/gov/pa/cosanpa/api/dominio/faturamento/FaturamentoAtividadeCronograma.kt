package br.gov.pa.cosanpa.api.dominio.faturamento

import jakarta.persistence.*
import java.util.*

@Entity
@Table(name = "fatur_ativ_cronograma", schema = "faturamento")
data class FaturamentoAtividadeCronograma(
    @Id
    @Column(name = "ftac_id")
    val id: Int,
    @Column(name = "ftac_dtprevista")
    val dataPrevista: Date?,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ftat_id")
    val faturamentoAtividade: FaturamentoAtividade,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ftcm_id")
    val faturamentoCronogramaGrupoMensal: FaturamentoGrupoCronogramaMensal
)
