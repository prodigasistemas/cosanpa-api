package br.gov.pa.cosanpa.api.dominio.atendimento_publico.ligacaoesgoto

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "ligacao_esgoto_situacao", schema = "atendimentopublico")
data class LigacaoEsgotoSituacao(
    @Id
    @Column(name = "lest_id")
    val id: Int = 0,
    @Column(name = "lest_dsligacaoesgotosituacao")
    val descricao: String?,
    @Column(name = "lest_icfaturamento")
    val indicadorFaturamentoSituacao: Short
)