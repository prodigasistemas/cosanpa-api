package br.gov.pa.cosanpa.api.service.cadastro.cliente

import br.gov.pa.cosanpa.api.dto.cadastro.cliente.ClienteImovelContaDTO
import br.gov.pa.cosanpa.api.repository.cadastro.cliente.ClienteRepository
import br.gov.pa.cosanpa.api.service.cadastro.endereco.EnderecoService
import br.gov.pa.cosanpa.api.view.cadastro.cliente.ClienteView
import br.gov.pa.cosanpa.api.view.cadastro.cliente.ClienteViewMapper
import org.springframework.stereotype.Service

@Service
class ClienteService(
    private val repository: ClienteRepository,
    private val enderecoService: EnderecoService,
    private val viewMapper: ClienteViewMapper
) {

    fun obterDadosCliente(idCliente: Int) = repository.obterDadosCliente(idCliente)

    fun obterClienteView(idCliente: Int) = viewMapper.map(obterDadosCliente(idCliente))

    fun obterEnderecoCliente(idCliente: Int)  = enderecoService.obterEnderecoCorrespondenciaCliente(idCliente)
}