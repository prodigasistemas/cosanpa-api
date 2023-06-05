package br.gov.pa.cosanpa.api.service.faturamento

import br.gov.pa.cosanpa.api.extensions.util.subtrairAnos
import br.gov.pa.cosanpa.api.repository.faturamento.ExtratoQuitacaoRepository
import br.gov.pa.cosanpa.api.util.ConstantesSistema
import org.springframework.stereotype.Service

@Service
class ExtratoQuitacaoService(
    private val repository: ExtratoQuitacaoRepository
) {

    fun obterMensagemExtratoQuitacaoImovel(idImovel: Int, anoReferencia: Int): String? {
        val anoAnterior = anoReferencia.subtrairAnos(1)
        val dto = repository.obterExtratoQuitacaoImovel(idImovel, anoAnterior)

        return dto?.let {
            if (it.indicadorImpressaoNaConta == ConstantesSistema.NAO) {
                "Em cumprimento a lei 12.007/2009, declaramos quitados os debitos de consumo de agua e/ou esgoto do ano de $anoAnterior."
            } else {
                null
            }
        }
    }


}
