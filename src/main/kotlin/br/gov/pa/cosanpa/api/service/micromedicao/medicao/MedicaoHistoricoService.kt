package br.gov.pa.cosanpa.api.service.micromedicao.medicao

import br.gov.pa.cosanpa.api.dto.micromedicao.medicao.MedicaoHistoricoDTO
import br.gov.pa.cosanpa.api.repository.micromedicao.medicao.MedicaoHistoricoRepository
import br.gov.pa.cosanpa.api.view.micromedicao.medicao.MedicaoHistoricoViewMapper
import org.springframework.stereotype.Service

@Service
class MedicaoHistoricoService(
    private val repository: MedicaoHistoricoRepository,
    private val viewMapper: MedicaoHistoricoViewMapper
) {

    fun obterDadosMedicaoHistorico(
        idImovel: Int,
        referencia: Int
    ) = repository.obterDadosMedicaoHistoricoPorImovelId(referencia, idImovel)

    fun obterMedicaoHistoricoView(dto: MedicaoHistoricoDTO) = viewMapper.mapMedicaoHistorico(dto)

    fun obterMedicaoTipoView(idMedicaoTipo: Int) = viewMapper.map(repository.obterDadosMedicaoTipo(idMedicaoTipo))

}