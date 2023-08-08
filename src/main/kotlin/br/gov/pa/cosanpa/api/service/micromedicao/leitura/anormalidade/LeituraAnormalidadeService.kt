package br.gov.pa.cosanpa.api.service.micromedicao.leitura.anormalidade

import br.gov.pa.cosanpa.api.repository.micromedicao.leitura.anormalidade.LeituraAnormalidadeRepository
import br.gov.pa.cosanpa.api.view.IView
import br.gov.pa.cosanpa.api.view.micromedicao.leitura.anormalidade.LeituraAnormalidadeViewMapper
import org.springframework.stereotype.Service

@Service
class LeituraAnormalidadeService(
    private val repository: LeituraAnormalidadeRepository,
    private val viewMapper: LeituraAnormalidadeViewMapper
) {

    fun obterLeituraAnormalidadeView(idLeituraAnormalidade: Int): IView {
        return viewMapper.map(repository.obterDadosLeituraAnormalidade(idLeituraAnormalidade))
    }
    fun obterLeituraAnormalidadeLeituraView(idLeituraAnormalidadeLeitura: Int): IView {
        return viewMapper.map(repository.obterDadosLeituraAnormalidadeLeitura(idLeituraAnormalidadeLeitura))
    }

    fun obterLeituraAnormalidadeConsumoView(idLeituraAnormalidadeConsumo: Int): IView {
        return viewMapper.map(repository.obterDadosLeituraAnormalidadeConsumo(idLeituraAnormalidadeConsumo))
    }
}