package br.gov.pa.cosanpa.api.service.faturamento.qualidadeagua

import br.gov.pa.cosanpa.api.dominio.faturamento.qualidadeagua.QualidadeAguaPadrao
import br.gov.pa.cosanpa.api.repository.faturamento.qualidadeagua.QualidadeAguaPadraoRepository
import org.springframework.stereotype.Service

@Service
class QualidadeAguaPadraoService(
    private val repository: QualidadeAguaPadraoRepository
) {

    fun obterQualidadeAguaPadrao(): QualidadeAguaPadrao = repository.getReferenceById(QualidadeAguaPadrao.ID)
}