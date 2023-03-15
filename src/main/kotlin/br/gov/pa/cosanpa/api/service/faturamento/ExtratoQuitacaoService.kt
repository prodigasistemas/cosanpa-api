package br.gov.pa.cosanpa.api.service.faturamento

import br.gov.pa.cosanpa.api.extensions.util.converterReferenciaParaLocalDate
import br.gov.pa.cosanpa.api.extensions.util.conveterLocalDateParaReferencia
import br.gov.pa.cosanpa.api.repository.faturamento.ExtratoQuitacaoRepository
import br.gov.pa.cosanpa.api.util.ConstantesSistema
import org.springframework.stereotype.Service

@Service
class ExtratoQuitacaoService(
    private val repository: ExtratoQuitacaoRepository
) {

    fun obterMensagemExtratoQuitacaoImovel(idImovel: Int, anoReferencia: Int): String {
        val anoAnterior = obterAnoAnterior(anoReferencia)
        val dto = repository.obterExtratoQuitacaoImovel(idImovel, anoAnterior)

        val mensagem: String = if (dto.indicadorImpressaoNaConta == ConstantesSistema.NAO) {
            "Em cumprimento a lei 12.007/2009, declaramos quitados os debitos de consumo de agua e/ou esgoto do ano de ${dto.anoReferencia}."
        } else {
            ""
        }
        return mensagem
    }

    private fun obterAnoAnterior(anoReferencia: Int) = anoReferencia.converterReferenciaParaLocalDate()
                                                        .minusYears(1)
                                                        .conveterLocalDateParaReferencia()
                                                        .toString()
                                                        .substring(0, 4)
                                                        .toInt()
}
