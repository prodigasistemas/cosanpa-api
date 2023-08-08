package br.gov.pa.cosanpa.api.dto.faturamento.conta

import java.math.BigDecimal
import java.time.LocalDate

data class ContaDTO(
    val id: Int? = null,
    val referencia: Int? = null,
    val dataVencimento: LocalDate? = null,
    val dataValidade: LocalDate? = null,
    val digitoVerificador: Int? = null,
    val lote: Int? = null,
    val sublote: Int? = null,
    val percentualEsgoto: BigDecimal? = null,
    val codigoSetorComercial: Int? = null,
    val quadra: Int? = null,
    val idDebitoCreditoSituacaoAtual: Int? = null,
    val idDebitoCreditoSituacaoAnterior: Int? = null,
    val idImovel: Int? = null,
    val idImovelPerfil: Int? = null,
    val idLocalidade: Int? = null,
    val idLigacaoAguaSituacao: Int? = null,
    val idLigacaoEsgotoSituacao: Int? = null,
    val idConsumoTarifa: Int? = null,
    val idFaturamentoGrupo: Int? = null
)