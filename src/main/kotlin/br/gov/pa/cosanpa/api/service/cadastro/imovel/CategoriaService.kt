package br.gov.pa.cosanpa.api.service.cadastro.imovel

import br.gov.pa.cosanpa.api.dto.Dto
import br.gov.pa.cosanpa.api.repository.cadastro.imovel.ImovelRepository
import br.gov.pa.cosanpa.api.view.cadastro.imovel.categoria.CategoriaViewMapper
import org.springframework.stereotype.Service

@Service
class CategoriaService(
    private val imovelRepository: ImovelRepository,
    private val viewMapper: CategoriaViewMapper
) {
    fun obterDadosCategorias(idImovel: Int) = imovelRepository.obterColecaoDadosCategoriasPorImovel(idImovel)

    fun obterCategoriasView(idImovel: Int) = obterDadosCategorias(idImovel).map { categoriaDTO ->
        viewMapper.mapCategoria(categoriaDTO)
    }

    fun obterColecaoIdDescricaoCategorias() = imovelRepository.obterColecaoIdDescricaoCategoriasEmUso()

    fun obterCategoriaViewSimples(dto: Dto) = viewMapper.map(dto)

}
