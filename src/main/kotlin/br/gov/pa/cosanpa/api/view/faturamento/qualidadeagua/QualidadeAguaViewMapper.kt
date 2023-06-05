package br.gov.pa.cosanpa.api.view.faturamento.qualidadeagua

import br.gov.pa.cosanpa.api.dominio.faturamento.qualidadeagua.QualidadeAguaPadrao
import br.gov.pa.cosanpa.api.dto.faturamento.QualidadeAguaDTO
import br.gov.pa.cosanpa.api.view.DtoViewMapper
import br.gov.pa.cosanpa.api.view.View
import org.springframework.stereotype.Component
import java.math.BigDecimal

@Component
class QualidadeAguaViewMapper : DtoViewMapper {

    fun mapQualidadeAguaPadrao(entity: QualidadeAguaPadrao): QualidadeAguaPadraoView {
        return QualidadeAguaPadraoView(
            id = entity.id,
            descricaoPadraoTurbidez = entity.descricaoPadraoTurbidez ?: "",
            descricaoPadraoPh = entity.descricaoPadraoPh ?: "",
            descricaoPadraoCor = entity.descricaoPadraoCor ?: "",
            descricaoPadraoCloro = entity.descricaoPadraoCloro ?: "",
            descricaoPadraoFluor = entity.descricaoPadraoFluor ?: "",
            descricaoPadraoFerro = entity.descricaoPadraoFerro ?: "",
            descricaoPadraoColiformesTotais = entity.descricaoPadraoColiformesTotais ?: "",
            descricaoPadraoColiformesFecais = entity.descricaoPadraoColiformesFecais ?: "",
            descricaoNitrato = entity.descricaoNitrato ?: "",
            descricaoPadraoColiformesTermotolerantes = entity.descricaoPadraoColiformesTermotolerantes ?: "",
        )
    }

    fun mapQualidadeAgua(entity: QualidadeAguaDTO): Map<String, Any> {
        val qualidadeAguaView = QualidadeAguaView(
            id = entity.id ?: 0,
            anoMesReferencia = entity.anoMesReferencia ?: 0,
            numeroIndiceTurbidez = entity.numeroIndiceTurbidez ?: BigDecimal.ZERO,
            numeroCloroResidual = entity.numeroCloroResidual ?: BigDecimal.ZERO,
            numeroIndicePh = entity.numeroIndicePh ?: BigDecimal.ZERO,
            numeroIndiceCor = entity.numeroIndiceCor ?: BigDecimal.ZERO,
            numeroIndiceFluor = entity.numeroIndiceFluor ?: BigDecimal.ZERO,
            numeroIndiceFerro = entity.numeroIndiceFerro ?: BigDecimal.ZERO,
            numeroIndiceColiformesTotais = entity.numeroIndiceColiformesTotais ?: BigDecimal.ZERO,
            numeroIndiceColiformesFecais = entity.numeroIndiceColiformesFecais ?: BigDecimal.ZERO,
            numeroNitrato = entity.numeroNitrato ?: BigDecimal.ZERO,
            numeroIndiceAlcalinidade = entity.numeroIndiceAlcalinidade ?: BigDecimal.ZERO,
            quantidadeTurbidezExigidas = entity.quantidadeTurbidezExigidas ?: 0,
            quantidadeTurbidezAnalisadas = entity.quantidadeTurbidezAnalisadas ?: 0,
            quantidadeTurbidezConforme = entity.quantidadeTurbidezConforme ?: 0,
            quantidadeCorExigidas = entity.quantidadeCorExigidas ?: 0,
            quantidadeCorAnalisadas = entity.quantidadeCorAnalisadas ?: 0,
            quantidadeCorConforme = entity.quantidadeCorConforme ?: 0,
            quantidadeCloroExigidas = entity.quantidadeCloroExigidas ?: 0,
            quantidadeCloroAnalisadas = entity.quantidadeCloroAnalisadas ?: 0,
            quantidadeCloroConforme = entity.quantidadeCloroConforme ?: 0,
            quantidadeFluorExigidas = entity.quantidadeFluorExigidas ?: 0,
            quantidadeFluorAnalisadas = entity.quantidadeFluorAnalisadas ?: 0,
            quantidadeFluorConforme = entity.quantidadeFluorConforme ?: 0,
            quantidadeColiformesTotaisExigidas = entity.quantidadeColiformesTotaisExigidas ?: 0,
            quantidadeColiformesTotaisAnalisadas = entity.quantidadeColiformesTotaisAnalisadas ?: 0,
            quantidadeColiformesTotaisConforme = entity.quantidadeColiformesTotaisConforme ?: 0,
            quantidadeColiformesFecaisExigidas = entity.quantidadeColiformesFecaisExigidas ?: 0,
            quantidadeColiformesFecaisAnalisadas = entity.quantidadeColiformesFecaisAnalisadas ?: 0,
            quantidadeColiformesFecaisConforme = entity.quantidadeColiformesFecaisConforme ?: 0,
            quantidadeColiformesTermotolerantesExigidas = entity.quantidadeColiformesTermotolerantesExigidas ?: 0,
            numeroIndiceColiformesTermotolerantes = entity.numeroIndiceColiformesTermotolerantes ?: 0,
            quantidadeColiformesTermotolerantesAnalisadas = entity.quantidadeColiformesTermotolerantesAnalisadas ?: 0,
            quantidadeColiformesTermotolerantesConforme = entity.quantidadeColiformesTermotolerantesConforme ?: 0,
            quantidadeAlcalinidadeExigidas = entity.quantidadeAlcalinidadeExigidas ?: 0,
            quantidadeAlcalinidadeAnalisadas = entity.quantidadeAlcalinidadeAnalisadas ?: 0,
            quantidadeAlcalinidadeConforme = entity.quantidadeAlcalinidadeConforme ?: 0
        )

        val fonteCaptacaoView = View(
            id = entity.idFonteCaptacao ?: 0,
            descricao = entity.descricaoFonteCaptacao ?: ""
        )
        val map = mutableMapOf<String, Any>()
        map["qualidadeAgua"] = qualidadeAguaView
        map["fonteCaptacaoView"] = fonteCaptacaoView

        return map
    }
}
