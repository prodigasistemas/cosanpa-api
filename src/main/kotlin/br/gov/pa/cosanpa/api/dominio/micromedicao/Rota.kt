package br.gov.pa.cosanpa.api.dominio.micromedicao

import br.gov.pa.cosanpa.api.dominio.cadastro.Empresa
import br.gov.pa.cosanpa.api.dominio.cadastro.localidade.SetorComercial
import br.gov.pa.cosanpa.api.dominio.faturamento.FaturamentoGrupo
import jakarta.persistence.*

@Entity
@Table(name = "rota", schema = "micromedicao")
data class Rota(
    @Id
    @Column(name = "rota_id")
    val id: Int = 0,
    @Column(name = "rota_cdrota")
    val codigo: Int = 0,
    @Column(name = "rota_icuso")
    val indicadorUso: Int = 0,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "empr_id")
    val empresa: Empresa,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "leit_id")
    val leiturista: Leiturista,

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "stcm_id")
    val setorComercial: SetorComercial,

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ftgr_id")
    val grupo: FaturamentoGrupo
)