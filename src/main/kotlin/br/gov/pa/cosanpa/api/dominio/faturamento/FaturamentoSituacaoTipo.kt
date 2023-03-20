package br.gov.pa.cosanpa.api.dominio.faturamento

import br.gov.pa.cosanpa.api.dominio.micromedicao.leitura.LeituraAnormalidadeConsumo
import br.gov.pa.cosanpa.api.dominio.micromedicao.leitura.LeituraAnormalidadeLeitura
import jakarta.persistence.*

@Entity
@Table(name = "fatur_situacao_tipo", schema = "faturamento")
data class FaturamentoSituacaoTipo(
    @Id
    @Column(name = "ftst_id")
    val id: Int,
    @Column(name = "ftst_dsfaturamentosituacaotipo")
    val descricao: String?,
    @Column(name = "ftst_icvalidoagua")
    val indicadorValidoAgua: Short?,
    @Column(name = "ftst_icvalidoesgoto")
    val indicadorValidoEsgoto: Short?,
    @Column(name = "ftst_icfaturamentoparalisacao")
    val indicadorParalisacaoFaturamento: Short?,
    @Column(name = "ftst_icLeituraparalisacao")
    val indicadorParalisacaoLeitura: Short?,

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "lacs_idconsacobrarcomleit", referencedColumnName = "lacs_id", insertable = false, updatable = false)
    val leituraAnormalidadeConsumoComLeitura: LeituraAnormalidadeConsumo,

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "lacs_idconsacobrarsemleit", referencedColumnName = "lacs_id", insertable = false, updatable = false)
    val leituraAnormalidadeConsumoSemLeitura: LeituraAnormalidadeConsumo,

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "lacs_idconsacobrarcomleit", referencedColumnName = "lalt_id", insertable = false, updatable = false)
    val leituraAnormalidadeLeituraComLeitura: LeituraAnormalidadeLeitura,

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "lacs_idconsacobrarsemleit", referencedColumnName = "lalt_id", insertable = false, updatable = false)
    val leituraAnormalidadeLeituraSemLeitura: LeituraAnormalidadeLeitura,
) {
    companion object {
        const val PARALISAR_EMISSAO_CONTAS = 1
        const val PARALISAR_LEITURA_FATURAR_MEDIA = 2
        const val PARALISAR_LEITURA_FATURAR_TAXA_MINIMA = 3
        const val FATURAR_NORMAL = 5
        const val PARALISAR_FATURAMENTO_DE_ESGOTO = 12
        const val FATURAR_MEDIA_RECADASTRAMENTO = 14
        const val SITUACAO_ESPECIAL_BOLSA_AGUA = 47
    }
}
