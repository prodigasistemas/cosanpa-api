package br.gov.pa.cosanpa.api.service.cadastro.endereco

import br.gov.pa.cosanpa.api.extensions.cadastro.endereco.formatarEndereco
import br.gov.pa.cosanpa.api.repository.cadastro.cliente.ClienteRepository
import br.gov.pa.cosanpa.api.repository.cadastro.imovel.ImovelRepository
import br.gov.pa.cosanpa.api.view.endereco.DadosEnderecoMapperCliente
import br.gov.pa.cosanpa.api.view.endereco.DadosEnderecoMapperImovel
import org.springframework.stereotype.Service

@Service
class EnderecoService(
    private val imovelRepository: ImovelRepository,
    private val clienteRepository: ClienteRepository,
    private val dadosEnderecoViewMapperCliente: DadosEnderecoMapperCliente,
    private val dadosEnderecoViewMapperImovel: DadosEnderecoMapperImovel
) {

    fun pesquisarEnderecoFormatadoImovel(id: Int) : String {
        val imovel = imovelRepository.obterDadosEndereco(id)
        val dadosEndereco = dadosEnderecoViewMapperImovel.map(imovel)
        return dadosEndereco.formatarEndereco()
    }

    fun pesquisarEnderecoCorrespondencia(id: Int) : String {
        val clienteEndereco = clienteRepository.obterDadosEnderecoCorrespondencia(id, 1)
        val dadosEndereco = dadosEnderecoViewMapperCliente.map(clienteEndereco)
        return dadosEndereco.formatarEndereco()
    }

}