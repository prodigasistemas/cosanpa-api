package br.gov.pa.cosanpa.api.service.imovel

import br.gov.pa.cosanpa.api.extensions.endereco.formatarEndereco
import br.gov.pa.cosanpa.api.repository.imovel.ImovelRepository
import org.springframework.stereotype.Service

@Service
class ImovelService(
    private val repository: ImovelRepository
) {

    fun pesquisarEnderecoFormatado(id: Int) : String {
        val imovel = repository.pesquisarEnderecoFormatado(id)
        println(imovel.formatarEndereco())
        return imovel.formatarEndereco()
    }

}
