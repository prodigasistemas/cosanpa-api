package br.gov.pa.cosanpa.api.service.micromedicao.consumo

import br.gov.pa.cosanpa.api.repository.micromedicao.consumohistorico.ConsumoHistoricoRepository
import br.gov.pa.cosanpa.api.dto.micromedicao.consumo.ConsumoHistoricoDTO
import org.springframework.stereotype.Service

@Service
class ConsumoHistoricoService(
    private val repository: ConsumoHistoricoRepository
) {

    fun obterListaConsumos(
        idImovel: Int,
        idLigacao: Int,
        amReferenciaInicial: Int,
        amReferenciaFinal: Int
    ): List<ConsumoHistoricoDTO> = repository.obterVolumeMedioAguaEsgoto(idImovel, idLigacao, amReferenciaInicial, amReferenciaFinal)
}
