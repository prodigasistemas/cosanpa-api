package br.gov.pa.cosanpa.api.repository.faturamento.conta

import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.TestPropertySource

@DataJpaTest
@TestPropertySource(locations = ["/application.yml"])
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ContaRepositoryTest {

    @Autowired
    private lateinit var repo: ContaRepository

    @Test
    fun `dado o id do Imovel e a Referencia, entao retorna dados de conta em Dto`(){
        val dto = repo.obterContaPreFaturada(7389353, 202303)
        assertNotNull(dto)
        println(dto)
    }

    @Test
    fun `dado o id de conta e a referencia, entao retorna lista de Dto de ClienteConta`(){
        val dto = repo.obterColecaoClienteContas(114936531, 202303)
        assertNotNull(dto)
        println(dto)
    }


    @Test
    fun `dado um id de conta, retorna Dto de dados de categoria`(){
        val categoriaDTOS = repo.obterColecaoDadosCategoriasPorContaId(114936531)
        assertNotNull(categoriaDTOS)
    }
}