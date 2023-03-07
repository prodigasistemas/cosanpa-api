package br.gov.pa.cosanpa.api.dominio.arrecadacao

import br.gov.pa.cosanpa.api.dominio.cadastro.imovel.Imovel
import jakarta.persistence.*
import java.util.Date

@Entity
@Table(name = "debito_automatico", schema = "arrecadacao")
data class DebitoAutomatico(
    @Id
    @Column(name = "deba_id")
    val id: Int = 0,
    @Column(name = "deba_dsidentificacaoclientebco")
    val identificacaoClienteBanco: String?,
    @Column(name = "deba_dtexclusao")
    val dataExclusao: Date?,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "imov_id")
    val imovel: Imovel,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "agen_id")
    val agencia: Agencia
)
