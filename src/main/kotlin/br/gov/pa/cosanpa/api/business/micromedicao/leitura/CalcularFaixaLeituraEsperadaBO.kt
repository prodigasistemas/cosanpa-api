package br.gov.pa.cosanpa.api.business.micromedicao.leitura

import br.gov.pa.cosanpa.api.dto.micromedicao.hidrometro.HidrometroDTO
import br.gov.pa.cosanpa.api.dto.micromedicao.leitura.FaixaLeituraDTO
import br.gov.pa.cosanpa.api.dto.micromedicao.medicao.MedicaoHistoricoDTO
import br.gov.pa.cosanpa.api.extensions.util.arredondar
import java.math.BigDecimal
import kotlin.math.pow

class CalcularFaixaLeituraEsperadaBO(
    private val media: Int,
    private val hidrometroDTO: HidrometroDTO,
    private val medicaoHistorico: MedicaoHistoricoDTO?,
    private val leituraAnteriorPesquisada: Int?
) {

    private val leituraAnterior = leituraAnteriorPesquisada?.let {
        BigDecimal(leituraAnteriorPesquisada)
    } ?: BigDecimal(obterLeituraAnterior(medicaoHistorico))

    private fun obterLeituraAnterior(medicaoHistorico: MedicaoHistoricoDTO?): Int {
        medicaoHistorico?.leituraAnteriorInformada?.let { leitAntInfor ->
            medicaoHistorico.leituraAtualInformada?.let { leitAtualFatu ->
                if (leitAntInfor == leitAtualFatu) return leitAntInfor
                else return medicaoHistorico.leituraAnterioFaturamento ?: 0
            }
        }
        return 0
    }

    fun calcular(): FaixaLeituraDTO {
        val faixaInicial: BigDecimal
        val faixaFinal: BigDecimal
        val mediaConsumo = BigDecimal(media)

        if (media <= 10) {
            faixaInicial = leituraAnterior
            faixaFinal = leituraAnterior.add(mediaConsumo).add(BigDecimal(10))
        } else if (media <= 20) {
            faixaInicial = leituraAnterior.add((BigDecimal(0.4)).multiply(mediaConsumo))
            faixaFinal = leituraAnterior.add((BigDecimal(1.6)).multiply(mediaConsumo))
        } else if (media <= 45) {
            faixaInicial = leituraAnterior.add((BigDecimal(0.5)).multiply(mediaConsumo))
            faixaFinal = leituraAnterior.add((BigDecimal(1.5)).multiply(mediaConsumo))
        } else if (media <= 100) {
            faixaInicial = leituraAnterior.add((BigDecimal(0.6)).multiply(mediaConsumo))
            faixaFinal = leituraAnterior.add((BigDecimal(1.4)).multiply(mediaConsumo))
        } else {
            faixaInicial = leituraAnterior.add((BigDecimal(0.7)).multiply(mediaConsumo))
            faixaFinal = leituraAnterior.add((BigDecimal(1.3)).multiply(mediaConsumo))
        }

        val faixaLeituraDTO = FaixaLeituraDTO(faixaInicial.arredondar(), faixaFinal.arredondar())

        return verificarViradaHidrometroFaixaEsperada(hidrometroDTO, faixaLeituraDTO)
    }

    private fun verificarViradaHidrometroFaixaEsperada(
        hidrometroDTO: HidrometroDTO,
        faixaLeituraDTO: FaixaLeituraDTO
    ): FaixaLeituraDTO {
        var faixaInicial = faixaLeituraDTO.faixaInicial
        var faixaFinal = faixaLeituraDTO.faixaFinal
        hidrometroDTO.let { hidrometro ->
            hidrometro.numeroDigitosLeitura?.let { numeroDigitosLeitura ->

                val valor = 10.0.pow(numeroDigitosLeitura.toDouble())
                val valorDigitos = valor.toInt() - 1

                if (faixaInicial > valorDigitos) faixaInicial -= valor.toInt()
                if (faixaFinal > valorDigitos) faixaFinal -= valor.toInt()
            }
            return FaixaLeituraDTO(faixaInicial, faixaFinal)
        }
    }
}
