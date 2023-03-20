package br.gov.pa.cosanpa.api.dominio.faturamento

import br.gov.pa.cosanpa.api.dominio.cadastro.imovel.Imovel
import jakarta.persistence.*

@Entity
@Table(name = "mov_conta_prefaturada", schema = "faturamento")

data class MovimentoContaPrefaturada(
    @Id
    @Column(name = "mcpf_id")
    val id: Int,
    @Column(name = "mcpf_ammovimento")
    val anoMesReferenciaPreFaturamento: Int,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "imov_id")
    val imovel: Imovel
)