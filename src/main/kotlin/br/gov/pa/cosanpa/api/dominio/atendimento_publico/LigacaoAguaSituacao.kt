package br.gov.pa.cosanpa.api.dominio.atendimento_publico

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "ligacao_agua_situacao", schema = "atendimentopublico")
data class LigacaoAguaSituacao(
    @Id
    @Column(name = "last_id")
    val id: Int,
    @Column(name = "last_dsligacaoaguasituacao")
    val descricao: String?,
    @Column(name = "last_icfaturamento")
    val indicadorFaturamentoSituacao: Short
)
