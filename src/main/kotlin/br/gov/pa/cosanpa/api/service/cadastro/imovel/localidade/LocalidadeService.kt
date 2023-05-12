package br.gov.pa.cosanpa.api.service.cadastro.imovel.localidade

import br.gov.pa.cosanpa.api.repository.cadastro.imovel.localidade.LocalidadeRepository
import br.gov.pa.cosanpa.api.view.DtoViewMapper
import br.gov.pa.cosanpa.api.view.cadastro.imovel.localidade.LocalidadeViewMapper
import org.springframework.stereotype.Service

@Service
class LocalidadeService(
    private val repository: LocalidadeRepository,
    private val viewMapper: LocalidadeViewMapper,
    private val dtoViewMapper: DtoViewMapper
) {

    fun obterDadosLocalidade(idLocalidade: Int) = repository.obterDadosLocalidade(idLocalidade)

    fun obterDadosGerenciaRegional(idGerenciaRegional: Int) = repository.obterDadosGerenciaRegional(idGerenciaRegional)

    fun obterLocalidadeView(idLocalidade: Int) = viewMapper.map(obterDadosLocalidade(idLocalidade))

    fun obterGerenciaRegionalView(idGerenciaRegional: Int) = dtoViewMapper.map(obterDadosGerenciaRegional(idGerenciaRegional))

}
