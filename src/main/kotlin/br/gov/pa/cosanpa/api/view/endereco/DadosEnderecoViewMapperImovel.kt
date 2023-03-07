package br.gov.pa.cosanpa.api.view.endereco

import br.gov.pa.cosanpa.api.dominio.cadastro.imovel.Imovel
import br.gov.pa.cosanpa.api.util.Mapper
import org.springframework.stereotype.Component

@Component
class DadosEnderecoViewMapperImovel : Mapper<Imovel, DadosEndereco> {
    override fun map(entity: Imovel): DadosEndereco {
        val logradouro = entity.logradouroCep?.logradouro
        val bairro = entity.logradouroBairro?.bairro
        return DadosEndereco(
            logradouro = logradouro,
            logradouroTipo = logradouro?.logradouroTipo,
            logradouroTitulo = logradouro?.logradouroTitulo,
            bairro = bairro,
            municipio = bairro?.municipio,
            unidadeFederacao = bairro?.municipio?.unidadeFederacao,
            enderecoReferencia = entity.enderecoReferencia,
            cep = entity.logradouroCep?.cep,
            imovel = entity,
            logradouroCep = entity.logradouroCep,
            logradouroBairro = entity.logradouroBairro,
            perimetroInicial = entity.perimetroInicial,
            perimetroFinal = entity.perimetroFinal
        )
    }

}
