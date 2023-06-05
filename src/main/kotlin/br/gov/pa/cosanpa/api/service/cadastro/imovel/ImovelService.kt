package br.gov.pa.cosanpa.api.service.cadastro.imovel

import br.gov.pa.cosanpa.api.extensions.cadastro.imovel.formatarInscricao
import br.gov.pa.cosanpa.api.repository.cadastro.imovel.ImovelRepository
import br.gov.pa.cosanpa.api.view.cadastro.imovel.ImovelViewMapper
import org.springframework.stereotype.Service

@Service
class ImovelService(
    private val repository: ImovelRepository,
    private val viewMapper: ImovelViewMapper,

) {
    fun obterConsumoTarifaImovel(idImovel: Int) = repository.obterIdConsumoTarifaPorImovel(idImovel)

    fun obterIdsImovelPorRota(idRota: Int) = repository.obterColecaoIdsImoveis(idRota)

    fun obterDadosImovel(idImovel: Int) = repository.obterDadosImovelGerarDados(idImovel)

    fun obterImovelView(idImovel: Int) = obterDadosImovel(idImovel)?.let { viewMapper.mapImovel(it) }

    fun obterDadosClienteImovel(idImovel: Int) = repository.obterColecaoClienteImoveis(idImovel)

    fun obterDadosInscricao(idImovel: Int) = repository.obterDadosInscricao(idImovel).formatarInscricao()

    fun obterDadosImovelContaEnvio(idImovelContaEnvio: Int) = repository.obterDadosImovelContaEnvio(idImovelContaEnvio)

    fun obterImovelContaEnvioView(idImovelContaEnvio: Int) = viewMapper.map(obterDadosImovelContaEnvio(idImovelContaEnvio))

    fun obterDadosPocoTipo(idPocoTipo: Int) = repository.obterDadosPocoTipo(idPocoTipo)

    fun obterPocoTipoView(idPocoTipo: Int) = viewMapper.map(obterDadosPocoTipo(idPocoTipo))

    fun obterGrupo(idImovel: Int) = repository.obterGrupoFaturamentoDoImovel(idImovel)

    fun obterSistemaAbastecimento(idImovel: Int) = repository.obterSistemaAbastecimentoImovel(idImovel)

    fun obterColecaoIdConsumoTarifaEmUso() = repository.obterColecaoIdConsumoTarifaEmUso()

}
