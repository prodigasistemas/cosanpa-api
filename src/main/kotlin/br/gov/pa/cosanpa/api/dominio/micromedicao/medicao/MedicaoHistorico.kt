package br.gov.pa.cosanpa.api.dominio.micromedicao.medicao

import br.gov.pa.cosanpa.api.dominio.atendimento_publico.ligacaoagua.LigacaoAgua
import br.gov.pa.cosanpa.api.dominio.cadastro.imovel.Imovel
import jakarta.persistence.*
import java.time.LocalDate

@Entity
@Table(name = "medicao_historico", schema = "micromedicao")
data class MedicaoHistorico(
    @Id
    @Column(name = "mdhi_id")
    val id : Int,
    @Column(name = "mdhi_dtleitantfatmt")
    val dataLeituraAnteriorFaturamento: LocalDate?,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "imov_id")
    val imovel: Imovel?,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lagu_id")
    val ligacaoAgua: LigacaoAgua?,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "medt_id")
    val medicaoTipo: MedicaoTipo
)