package br.gov.pa.cosanpa.api.service.endereco

import br.gov.pa.cosanpa.api.extensions.endereco.formatarEndereco
import br.gov.pa.cosanpa.api.repository.cliente.ClienteRepository
import br.gov.pa.cosanpa.api.repository.imovel.ImovelRepository
import br.gov.pa.cosanpa.api.view.endereco.DadosEnderecoViewMapperCliente
import br.gov.pa.cosanpa.api.view.endereco.DadosEnderecoViewMapperImovel
import org.springframework.stereotype.Service

@Service
class EnderecoService(
    private val imovelRepository: ImovelRepository,
    private val clienteRepository: ClienteRepository,
    private val dadosEnderecoViewMapperCliente: DadosEnderecoViewMapperCliente,
    private val dadosEnderecoViewMapperImovel: DadosEnderecoViewMapperImovel
) {

    fun pesquisarEnderecoFormatadoImovel(id: Int) : String {
        val imovel = imovelRepository.pesquisarEnderecoFormatado(id)
        val dadosEndereco = dadosEnderecoViewMapperImovel.map(imovel)
        return dadosEndereco.formatarEndereco()
    }

    fun pesquisarEnderecoCorrespondencia(id: Int) : String {
        val clienteEndereco = clienteRepository.pesquisarEnderecoCorrespondencia(id, 1)
        val dadosEndereco = dadosEnderecoViewMapperCliente.map(clienteEndereco)
        return dadosEndereco.formatarEndereco()
    }

}