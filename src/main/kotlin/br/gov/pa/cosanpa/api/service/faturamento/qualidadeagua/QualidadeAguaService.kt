package br.gov.pa.cosanpa.api.service.faturamento.qualidadeagua

import br.gov.pa.cosanpa.api.dominio.faturamento.qualidadeagua.QualidadeAguaPadrao
import br.gov.pa.cosanpa.api.repository.faturamento.qualidadeagua.QualidadeAguaPadraoRepository
import br.gov.pa.cosanpa.api.repository.faturamento.qualidadeagua.QualidadeAguaRepository
import br.gov.pa.cosanpa.api.view.faturamento.qualidadeagua.QualidadeAguaViewMapper
import org.springframework.stereotype.Service

@Service
class QualidadeAguaService(
    private val repository: QualidadeAguaRepository,
    private val padraoRepository: QualidadeAguaPadraoRepository,
    private val viewMapper: QualidadeAguaViewMapper
) {

    fun obterQualidadeAguaPadraoView() = viewMapper.mapQualidadeAguaPadrao(padraoRepository.getReferenceById(QualidadeAguaPadrao.ID))

    fun obterDadosQualidadeAgua(
        idLocalidade: Int?,
        idSetorComercial: Int?,
        idSistemaAbastecimento: Int?,
        referencia: Int
    ): Map<String, Any>? {
        val qualidadeAgua = repository.obterQualidadeAgua(
            idLocalidade = null,
            idSetorComercial = null,
            idSistemaAbastecimento = idSistemaAbastecimento,
            anoMesReferencia = referencia
        ) ?: repository.obterQualidadeAgua(
            idLocalidade = idLocalidade,
            idSetorComercial = null,
            idSistemaAbastecimento = null,
            anoMesReferencia = referencia
        ) ?: repository.obterQualidadeAgua(
            idLocalidade = null,
            idSetorComercial = null,
            idSistemaAbastecimento = null,
            anoMesReferencia = referencia
        )

        return qualidadeAgua?.let { viewMapper.mapQualidadeAgua(it) }
    }
}