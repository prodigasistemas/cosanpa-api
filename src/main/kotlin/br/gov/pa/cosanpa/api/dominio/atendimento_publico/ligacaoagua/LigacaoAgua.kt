package br.gov.pa.cosanpa.api.dominio.atendimento_publico.ligacaoagua

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "ligacao_agua", schema = "atendimento_publico")
data class LigacaoAgua(
    @Id
    @Column(name = "lagu_id")
    val id: Int,
    @Column(name = "lagu_nnconsumominimoagua")
    val numeroConsumoMinimoAgua: Int?
)