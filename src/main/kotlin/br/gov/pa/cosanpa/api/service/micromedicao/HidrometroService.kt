package br.gov.pa.cosanpa.api.service.micromedicao

import br.gov.pa.cosanpa.api.dto.micromedicao.hidrometro.HidrometroInstalacaoHistoricoDTO
import br.gov.pa.cosanpa.api.repository.micromedicao.hidrometro.HidrometroRepository
import br.gov.pa.cosanpa.api.view.IView
import br.gov.pa.cosanpa.api.view.micromedicao.hidrometro.HidrometroInstalacaoHistoricoView
import br.gov.pa.cosanpa.api.view.micromedicao.hidrometro.HidrometroViewMapper
import org.springframework.stereotype.Service

@Service
class HidrometroService(
    private val repository: HidrometroRepository,
    private val viewMapper: HidrometroViewMapper
) {

    fun obterDadosHidrometroInstalacaoHistoricoPorImovel(idImovel: Int): HidrometroInstalacaoHistoricoDTO? {
        return repository.obterDadosHidrometroInstalacaoHidrometroPorImovel(idImovel)
    }

    fun obterHidrometroInstalacaoHistoricoView(dto: HidrometroInstalacaoHistoricoDTO): HidrometroInstalacaoHistoricoView {
        return viewMapper.mapInstalacaoHistorico(dto)
    }

    fun obterDadosHidrometro(idHidrometro: Int) = repository.obterDadosHidrometro(idHidrometro)

    fun obterHidrometroView(idHidrometro: Int) = viewMapper.mapHidrometro(obterDadosHidrometro(idHidrometro))

    fun obterHidrometroProtecaoView(idHidrometroProtecao: Int): IView {
        return viewMapper.map(repository.obterDadosHidrometroProtecao(idHidrometroProtecao))
    }

    fun obterHidrometroLocalInstalacaoView(idHidrometroLocalInstalacao: Int): IView {
        return viewMapper.map(repository.obterDadosHidrometroLocalInstalacao(idHidrometroLocalInstalacao))
    }

    fun obterRateioTipoView(idRateioTipo: Int) = viewMapper.map(repository.obterDadosRateioTipo(idRateioTipo))
}