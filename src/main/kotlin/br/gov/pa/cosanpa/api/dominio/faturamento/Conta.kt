package br.gov.pa.cosanpa.api.dominio.faturamento

import br.gov.pa.cosanpa.api.dominio.atendimento_publico.LigacaoAguaSituacao
import br.gov.pa.cosanpa.api.dominio.atendimento_publico.LigacaoEsgotoSituacao
import br.gov.pa.cosanpa.api.dominio.cadastro.imovel.Imovel
import br.gov.pa.cosanpa.api.dominio.cadastro.cliente.ClienteConta
import br.gov.pa.cosanpa.api.dominio.cadastro.localidade.Localidade
import br.gov.pa.cosanpa.api.dominio.cadastro.localidade.Quadra
import br.gov.pa.cosanpa.api.dominio.cadastro.localidade.SetorComercial
import jakarta.persistence.*
import java.util.*

@Entity
@Table(name = "conta", schema = "faturamento")
data class Conta(
    @Id
    @Column(name = "cnta_id")
    val id: Int = 0,
    @Column(name = "cnta_dtvencimento")
    val dataVencimento: Date,
    @Column(name = "cnta_dtvalidade")
    val dataValidade: Date,
    @Column(name = "cnta_dgverificadorconta")
    val digitoVerificador: Int,
    @Column(name = "cnta_amreferenciaconta")
    val referencia: Int,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "loca_id")
    val localidade: Localidade,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "qdra_id")
    val quadra: Quadra,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stcm_id")
    val setorComercial: SetorComercial,

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "cnta_id")
    val cliente: List<ClienteConta>,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "imov_id")
    val imovel: Imovel,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "last_id")
    val ligacaoAguaSituacao: LigacaoAguaSituacao,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lest_id")
    val ligacaoEsgotoSituacao: LigacaoEsgotoSituacao
)
