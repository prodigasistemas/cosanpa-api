package br.gov.pa.cosanpa.api.dominio.faturamento.conta

import br.gov.pa.cosanpa.api.dominio.cadastro.localidade.GerencialRegional
import br.gov.pa.cosanpa.api.dominio.cadastro.localidade.Localidade
import br.gov.pa.cosanpa.api.dominio.cadastro.localidade.Quadra
import br.gov.pa.cosanpa.api.dominio.cadastro.localidade.SetorComercial
import br.gov.pa.cosanpa.api.dominio.faturamento.FaturamentoGrupo
import jakarta.persistence.*

@Entity
@Table(name = "conta_mensagem", schema = "faturamento")
data class ContaMensagem(
        @Id
        @Column(name = "ctms_id")
        val id: Int,
        @Column(name = "ctms_amreferenciafaturamento")
        val anoMesReferenciaFaturamento: Int?,
        @Column(name = "ctms_dscontamensagem01")
        val descricaoContaMensagem01: String,
        @Column(name = "ctms_dscontamensagem02")
        val descricaoContaMensagem02: String?,
        @Column(name = "ctms_dscontamensagem03")
        val descricaoContaMensagem03: String?,

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "ftgr_id")
        val faturamentoGrupo: FaturamentoGrupo,

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "greg_id")
        val gerenciaRegional: GerencialRegional,

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "loca_id")
        val localidade: Localidade,

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "qdra_id")
        val quadra: Quadra,

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "stcm_id")
        val setorComercial: SetorComercial,
)
