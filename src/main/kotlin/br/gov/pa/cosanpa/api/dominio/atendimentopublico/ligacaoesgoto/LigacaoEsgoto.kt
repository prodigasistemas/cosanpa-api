package br.gov.pa.cosanpa.api.dominio.atendimentopublico.ligacaoesgoto

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.math.BigDecimal
import java.time.LocalDate

@Entity
@Table(name = "ligacao_esgoto", schema = "atendimentopublico")
data class LigacaoEsgoto(
    @Id
    @Column(name = "lesg_id")
    val id: Int,
    @Column(name = "lesg_nnconsumominimoesgoto")
    val numeroConsumoMinimoEsgoto: Int?,
    @Column(name = "lesg_pccoleta")
    val percentualAguaConsumidaColetada: BigDecimal?,
    @Column(name = "lesg_dtligacao")
    val dataLigacao: LocalDate?,
)