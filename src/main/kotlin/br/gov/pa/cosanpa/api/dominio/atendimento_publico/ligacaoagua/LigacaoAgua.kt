package br.gov.pa.cosanpa.api.dominio.atendimento_publico.ligacaoagua

import br.gov.pa.cosanpa.api.dominio.micromedicao.hidrometro.HidrometroInstalacaoHistorico
import jakarta.persistence.*
import java.util.Date

@Entity
@Table(name = "ligacao_agua", schema = "atendimento_publico")
data class LigacaoAgua(
    @Id
    @Column(name = "lagu_id")
    val id: Int,
    @Column(name = "lagu_nnconsumominimoagua")
    val numeroConsumoMinimoAgua: Int?,
    @Column(name = "lagu_dtligacao")
    val dataLigacao: Date,
    @Column(name = "lagu_dtcorte")
    val dataCorte: Date,
    @Column(name = "lagu_nnlacre")
    val numeroLacre: String?,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hidi_id")
    val hidrometroInstalacaoHistorico: HidrometroInstalacaoHistorico
)