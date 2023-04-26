package br.gov.pa.cosanpa.api.dto.micromedicao.medicao

import br.gov.pa.cosanpa.api.dominio.atendimento_publico.ligacaoagua.LigacaoAgua
import java.time.LocalDate

data class MedicaoHistoricoDTO(
    val id : Int? = null,
    val anoMesReferencia: Int? = null,
    val dataLeituraAnteriorFaturamento: LocalDate? = null,
    val leituraAnteriorInformada: Int? = null,
    val leituraAnterioFaturamento: Int? = null,
    val dataLeituraAtualInformada: LocalDate? = null,
    val leituraAtualInformada: Int? = null,
    val dataLeituraAtualFaturamento: LocalDate? = null,
    val leituraAtualFaturamento: Int? = null,
    val consumoMedioHidrometro: Int? = null,
    val idImovel: Int? = null,
    val idLigacaoAgua: Int? = null,
    val idMedicaoTipo: Int? = null,
    val idLeituraSituacaoAnterior: Int? = null,
    val idLeituraSituacaoAtual: Int? = null
)
