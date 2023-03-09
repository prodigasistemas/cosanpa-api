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

        val amReferenciaFinal: LocalDate = anoMesReferencia.converterReferenciaParaLocalDate().minusMonths(1)
        val amReferenciaInicial: LocalDate = amReferenciaFinal.minusMonths(sp.numeroMesesMediaConsumo.toLong())

        val quantidadeMesesPeriodoInformado =
            amReferenciaInicial.datesUntil(amReferenciaFinal, Period.ofMonths(1)).count().toInt()

        val consumoHistoricoList = consumoHistoricoService.obterVolumeMedioAguaOuEsgoto(
            idImovel,
            idLigacaoTipo,
            amReferenciaInicial.conveterLocalDateParaReferencia(),
            amReferenciaFinal.conveterLocalDateParaReferencia()
        )

        return calcularMediaConsumo(consumoHistoricoList)

    }

    private fun calcularMediaConsumo(
        consumoHistoricoList: List<ConsumoHistoricoDTO>?,
    ): Int {
        var mediaConsumo = 0
        var consumo = 0
        var quantidadeMesesConsiderados = 0

        consumoHistoricoList?.let { dadosConsumo ->
            dadosConsumo.forEach { dado ->
                dado.numeroCalculoConsumoMedia?.let { consumo += it }
                quantidadeMesesConsiderados++
            }

            mediaConsumo = consumo / quantidadeMesesConsiderados
        }
        return mediaConsumo
    }
}
