package br.gov.pa.cosanpa.api.service.cadastro.endereco

import br.gov.pa.cosanpa.api.dominio.cadastro.cliente.ClienteEndereco
import br.gov.pa.cosanpa.api.extensions.cadastro.endereco.formatarEndereco
import br.gov.pa.cosanpa.api.repository.cadastro.cliente.ClienteRepository
import br.gov.pa.cosanpa.api.repository.cadastro.imovel.ImovelRepository
import org.springframework.stereotype.Service

@Service
class EnderecoService(
    private val imovelRepository: ImovelRepository,
    private val clienteRepository: ClienteRepository
) {

    fun pesquisarEnderecoFormatadoImovel(id: Int) : String {
        val enderecoDTO = imovelRepository.obterDadosEndereco(id)
        return enderecoDTO.formatarEndereco()
    }

    fun pesquisarEnderecoCorrespondencia(id: Int) : String {
        val enderecoDTO = clienteRepository.obterDadosEnderecoCorrespondencia(id, ClienteEndereco.INDICADOR_ENDERECO_CORRESPONDENCIA)
        return enderecoDTO.formatarEndereco()
    }

}