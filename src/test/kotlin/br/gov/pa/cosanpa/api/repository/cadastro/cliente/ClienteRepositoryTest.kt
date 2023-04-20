package br.gov.pa.cosanpa.api.repository.cadastro.cliente

import br.gov.pa.cosanpa.api.dominio.cadastro.cliente.ClienteEndereco
import br.gov.pa.cosanpa.api.extensions.cadastro.endereco.formatarEndereco
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.TestPropertySource

@DataJpaTest
@TestPropertySource(locations = ["/application.yml"])
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ClienteRepositoryTest {

    @Autowired
    private lateinit var repo: ClienteRepository

    @Test
    fun `dado um id de cliente, retorna DTO com informações de endereco`() {
        val enderecoDTO = repo.obterDadosEnderecoCorrespondencia(6759506, ClienteEndereco.INDICADOR_ENDERECO_CORRESPONDENCIA)
        assertNotNull(enderecoDTO)
        print(enderecoDTO.formatarEndereco())
    }

    @Test
    fun `dado um id de cliente, retorna DTO com dados de cliente`(){
        val dto = repo.obterDadosCliente(6759506)
        assertNotNull(dto)
    }
}