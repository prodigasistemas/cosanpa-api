package br.gov.pa.cosanpa.api.business.micromedicao.consumo

import br.gov.pa.cosanpa.api.dto.micromedicao.consumo.ConsumoHistoricoDTO
import br.gov.pa.cosanpa.api.extensions.util.converterReferenciaParaLocalDate
import br.gov.pa.cosanpa.api.extensions.util.conveterLocalDateParaReferencia
import br.gov.pa.cosanpa.api.service.cadastro.SistemaParametrosService
import br.gov.pa.cosanpa.api.service.micromedicao.consumo.ConsumoHistoricoService
import org.springframework.stereotype.Component
import java.time.LocalDate
import java.time.Period

@Component
class ObterMediaConsumoAguaEsgoto(
    private val sistemaParametrosService: SistemaParametrosService,
    private val consumoHistoricoService: ConsumoHistoricoService
) {

    fun obter(idImovel: Int, anoMesReferencia: Int, idLigacaoTipo: Int): Int {
        val sp = sistemaParametrosService.retornaParametrosDoSistema()
        val numeroMesesMaximoCalculoMedia = sp.numeroMesesMaximoCalculoMediaConsumo

        val amReferenciaFinal: LocalDate = anoMesReferencia.converterReferenciaParaLocalDate().minusMonths(1)
        val amReferenciaInicial: LocalDate = amReferenciaFinal.minusMonths(sp.numeroMesesMediaConsumo.toLong())
        val amReferenciaInicialMaximo: LocalDate =
            amReferenciaInicial.minusMonths(numeroMesesMaximoCalculoMedia.toLong())

        val quantidadeMesesPeriodoInformado =
            amReferenciaInicial.datesUntil(amReferenciaFinal, Period.ofMonths(1)).count().toInt()

        val consumoHistoricoList = consumoHistoricoService.obterVolumeMedioAguaOuEsgoto(
            idImovel,
            idLigacaoTipo,
            amReferenciaInicialMaximo.conveterLocalDateParaReferencia(),
            amReferenciaFinal.conveterLocalDateParaReferencia()
        )

        return calcularMediaConsumo(
            consumoHistoricoList,
            numeroMesesMaximoCalculoMedia,
            quantidadeMesesPeriodoInformado,
            amReferenciaFinal,
        )

    }

    private fun calcularMediaConsumo(
        consumoHistoricoList: List<ConsumoHistoricoDTO>?,
        numeroMesesMaximoCalculoMedia: Int,
        quantidadeMesesPeriodoInformado: Int,
        amReferenciaFinal: LocalDate,
    ): Int {
        var amReferenciaFinal1 = amReferenciaFinal
        var mediaConsumo = 0
        var quantidadeMesesRetroagidos = 0
        var consumo = 0
        var quantidadeMesesConsiderados = 0

        consumoHistoricoList?.let { dadosConsumo ->
            dadosConsumo.forEach { dado ->
                val referencia = dado.referencia?.converterReferenciaParaLocalDate()
                var retroagir: Boolean
                var fimCalculo = false

                while (!fimCalculo
                    && (quantidadeMesesRetroagidos <= numeroMesesMaximoCalculoMedia)
                    && (quantidadeMesesConsiderados < quantidadeMesesPeriodoInformado)
                ) {
                    if (amReferenciaFinal1 == referencia) {
                        dado.numeroCalculoConsumoMedia?.let { consumo += it }
                        quantidadeMesesConsiderados++
                        retroagir = false
                    } else {
                        retroagir = true
                    }
                    if (quantidadeMesesRetroagidos < numeroMesesMaximoCalculoMedia) {
                        if (retroagir) {
                            quantidadeMesesRetroagidos++
                        }
                        amReferenciaFinal1 = amReferenciaFinal1.minusMonths(1)
                    } else {
                        fimCalculo = true
                    }
                }

                if (quantidadeMesesConsiderados > 0) {
                    mediaConsumo = consumo / quantidadeMesesConsiderados
                }
            }
        }
        return mediaConsumo
    }
}
