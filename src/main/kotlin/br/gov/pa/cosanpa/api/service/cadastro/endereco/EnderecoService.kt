package br.gov.pa.cosanpa.api.service.cadastro.endereco

import br.gov.pa.cosanpa.api.dominio.cadastro.cliente.ClienteEndereco
import br.gov.pa.cosanpa.api.extensions.cadastro.endereco.formatarEndereco
import br.gov.pa.cosanpa.api.repository.cadastro.cliente.ClienteRepository
import br.gov.pa.cosanpa.api.repository.cadastro.imovel.ImovelRepository
import br.gov.pa.cosanpa.api.repository.cadastro.imovel.localidade.LocalidadeRepository
import org.springframework.stereotype.Service

@Service
class EnderecoService(
    private val imovelRepository: ImovelRepository,
    private val clienteRepository: ClienteRepository,
    val localidadeRepository: LocalidadeRepository
) {

    fun pesquisarEnderecoFormatadoImovel(idImovel: Int): String {
        val enderecoDTO = imovelRepository.obterDadosEndereco(idImovel)
        return enderecoDTO.formatarEndereco()
    }

    fun pesquisarEnderecoCorrespondenciaCliente(idCliente: Int): String {
        val enderecoDTO = clienteRepository.obterDadosEnderecoCorrespondencia(
            idCliente,
            ClienteEndereco.INDICADOR_ENDERECO_CORRESPONDENCIA
        )
        return enderecoDTO.formatarEndereco()
    }

    fun pesquisarEnderecoAtendimentoLocalidade(idLocalidade: Int): String {
        val enderecoDTO = localidadeRepository.obterDadosEnderecoAtendimento(idLocalidade)
        return enderecoDTO.formatarEndereco()
    }

}