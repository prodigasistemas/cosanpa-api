package br.gov.pa.cosanpa.api.actions.micromedicao

import br.gov.pa.cosanpa.api.dominio.cadastro.SistemaParametros
import br.gov.pa.cosanpa.api.extensions.util.converterReferenciaParaLocalDate
import br.gov.pa.cosanpa.api.extensions.util.conveterLocalDateParaReferencia
import br.gov.pa.cosanpa.api.service.SistemaParametrosService
import br.gov.pa.cosanpa.api.service.consumo.ConsumoHistoricoService
import br.gov.pa.cosanpa.api.service.imovel.ImovelService
import br.gov.pa.cosanpa.api.view.consumo.ConsumoHistoricoView
import org.springframework.stereotype.Component
import java.time.LocalDate
import java.time.Period

@Component
class ObterVolumeAguaEsgoto(
    private val sistemaParametrosService: SistemaParametrosService,
    private val consumoHistoricoService: ConsumoHistoricoService,
    private val imovelService: ImovelService
) {

    fun obterVolumeMedioAguaEsgoto(idImovel: Int, anoMesReferencia: Int, idLigacaoTipo: Int) {
        val sp = sistemasParametros()
        var quantidadeMesesRetroagidos = 0
        var consumo = 0
        var mediaConsumo = 0
        val numeroMesesMaximoCalculoMedia = sp.numeroMesesMaximoCalculoMediaConsumo

        var amReferenciaFinal: LocalDate = anoMesReferencia.converterReferenciaParaLocalDate().minusMonths(1)
        val amReferenciaInicial: LocalDate = amReferenciaFinal.minusMonths(sp.numeroMesesMediaConsumo.toLong())
        val amReferenciaInicialMaximo: LocalDate =
            amReferenciaInicial.minusMonths(numeroMesesMaximoCalculoMedia.toLong())

        val quantidadeMesesPeriodoInformado =
            amReferenciaInicial.datesUntil(amReferenciaFinal, Period.ofMonths(1)).count().toInt()
        var quantidadeMesesConsiderados = 0

        val consumoHistoricoList = obterVolumeMedioAguaOuEsgotoConsumoHistorico(
            idImovel,
            idLigacaoTipo,
            amReferenciaInicialMaximo.conveterLocalDateParaReferencia(),
            amReferenciaFinal.conveterLocalDateParaReferencia()
        )

        if (!consumoHistoricoList.isNullOrEmpty()) {
            mediaConsumo = calcularMediaConsumo(
                consumoHistoricoList,
                quantidadeMesesRetroagidos,
                numeroMesesMaximoCalculoMedia,
                quantidadeMesesConsiderados,
                quantidadeMesesPeriodoInformado,
                amReferenciaFinal,
                consumo,
                mediaConsumo
            )
        } else {

        }
    }

    private fun calcularMediaConsumo(
        consumoHistoricoList: List<ConsumoHistoricoView>?,
        quantidadeMesesRetroagidos: Int,
        numeroMesesMaximoCalculoMedia: Int,
        quantidadeMesesConsiderados: Int,
        quantidadeMesesPeriodoInformado: Int,
        amReferenciaFinal: LocalDate,
        consumo: Int,
        mediaConsumo: Int
    ) : Int {
        var quantidadeMesesRetroagidos1 = quantidadeMesesRetroagidos
        var quantidadeMesesConsiderados1 = quantidadeMesesConsiderados
        var amReferenciaFinal1 = amReferenciaFinal
        var consumo1 = consumo
        var mediaConsumo1 = mediaConsumo
        consumoHistoricoList?.let { dadosConsumo ->
            dadosConsumo.forEach { dado ->
                var referencia = dado.referencia?.converterReferenciaParaLocalDate()
                var retroagir: Boolean
                var fimCalculo = false

                while (!fimCalculo
                    && (quantidadeMesesRetroagidos1 <= numeroMesesMaximoCalculoMedia)
                    && (quantidadeMesesConsiderados1 < quantidadeMesesPeriodoInformado)
                ) {
                    if (amReferenciaFinal1 == referencia) {
                        dado.numeroCalculoConsumoMedia?.let { consumo1 += it }
                        quantidadeMesesConsiderados1++
                        retroagir = false
                    } else {
                        retroagir = true
                    }
                    if (quantidadeMesesRetroagidos1 < numeroMesesMaximoCalculoMedia) {
                        if (retroagir) {
                            quantidadeMesesRetroagidos1++
                        }
                        amReferenciaFinal1 = amReferenciaFinal1.minusMonths(1)
                    } else {
                        fimCalculo = true
                    }
                }

                if (quantidadeMesesConsiderados1 > 0) {
                    mediaConsumo1 = consumo1 / quantidadeMesesConsiderados1
                }
            }
        }
        return mediaConsumo1
    }


    fun sistemasParametros(): SistemaParametros = sistemaParametrosService.retornaParametrosDoSistema()

    fun obterVolumeMedioAguaOuEsgotoConsumoHistorico(
        idImovel: Int,
        idLigacao: Int,
        amReferenciaInicial: Int,
        amReferenciaFinal: Int
    ): List<ConsumoHistoricoView>? = consumoHistoricoService.obterVolumeMedioAguaOuEsgoto(
        idImovel,
        idLigacao,
        amReferenciaInicial,
        amReferenciaFinal
    )
}
