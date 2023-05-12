package br.gov.pa.cosanpa.api.service.atendimentopublico

import br.gov.pa.cosanpa.api.repository.atendimentopublico.ligacaoagua.LigacaoRepository
import br.gov.pa.cosanpa.api.view.atendimentopublico.LigacaoSituacaoViewMapper
import br.gov.pa.cosanpa.api.view.atendimentopublico.ligacaoagua.LigacaoAguaViewMapper
import br.gov.pa.cosanpa.api.view.atendimentopublico.ligacaoesgoto.LigacaoEsgotoViewMapper
import org.springframework.stereotype.Service

@Service
class LigacaoService(
    private val repository: LigacaoRepository,
    private val ligacaoAguaViewMapper: LigacaoAguaViewMapper,
    private val ligacaoEsgotoViewMapper: LigacaoEsgotoViewMapper,
    private val ligacaoSituacaoViewMapper:  LigacaoSituacaoViewMapper
) {
    fun obterDadosLigacaoAgua(idLigacaoAgua: Int) = repository.obterDadosLigacaoAgua(idLigacaoAgua)
    
    fun obterLigacaoAguaView(idLigacaoAgua: Int) = obterDadosLigacaoAgua(idLigacaoAgua)?.let {
        ligacaoAguaViewMapper.map(it)
    }

    fun obterDadosLigacaoEsgoto(idLigacaoEsgoto: Int) = repository.obterDadosLigacaoEsgoto(idLigacaoEsgoto)

    fun obterLigacaoEsgotoView(idLigacaoEsgoto: Int) = obterDadosLigacaoEsgoto(idLigacaoEsgoto)?.let {
        ligacaoEsgotoViewMapper.map(it)
    }

    fun obterDadosLigacaoAguaSituacao(idLigacaoAguaSituacao: Int) = repository.obterDadosLigacaoAguaSituacao(idLigacaoAguaSituacao)

    fun obterLigacaoAguaSituacaoView(idLigacaoAguaSituacao: Int) = ligacaoSituacaoViewMapper.map(obterDadosLigacaoAguaSituacao(idLigacaoAguaSituacao))

    fun obterDadosLigacaoEsgotoSituacao(idLigacaoEsgotoSituacao: Int) = repository.obterDadosLigacaoEsgotoSituacao(idLigacaoEsgotoSituacao)

    fun obterLigacaoEsgotoSituacaoView(idLigacaoEsgotoSituacao: Int) = ligacaoSituacaoViewMapper.mapLigacaoEsgotoSituacao(obterDadosLigacaoEsgotoSituacao(idLigacaoEsgotoSituacao))

}
