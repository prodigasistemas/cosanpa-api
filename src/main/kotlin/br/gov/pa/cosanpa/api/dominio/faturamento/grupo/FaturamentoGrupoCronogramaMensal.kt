package br.gov.pa.cosanpa.api.dominio.faturamento.grupo

import jakarta.persistence.*

@Entity
@Table(name = "fatur_grupo_crg_mensal", schema = "faturamento")
data class FaturamentoGrupoCronogramaMensal(
    @Id
    @Column(name = "ftcm_id")
    val id: Int,
    @Column(name = "ftcm_amreferencia")
    val anoMesReferencia: Int,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ftgr_id")
    val faturamentoGrupo: FaturamentoGrupo
)
