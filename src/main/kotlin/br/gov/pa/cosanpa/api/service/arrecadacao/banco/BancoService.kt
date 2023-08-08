package br.gov.pa.cosanpa.api.service.arrecadacao.banco

import br.gov.pa.cosanpa.api.repository.arrecadacao.banco.BancoRepository
import br.gov.pa.cosanpa.api.view.arrecadacao.banco.BancoViewMapper
import org.springframework.stereotype.Service

@Service
class BancoService(
    private val repository: BancoRepository,
    private val viewMapper: BancoViewMapper
) {

    fun obterDadosAgencia(idAgencia: Int) = repository.obterDadosAgencia(idAgencia)

    fun obterAgenciaView(idAgencia: Int) = viewMapper.mapAgencia(obterDadosAgencia(idAgencia))

    fun obterDadosBanco(idBanco: Int) = repository.obterDadosBanco(idBanco)

    fun obterBancoView(idBanco: Int) = viewMapper.map(obterDadosBanco(idBanco))

}