package br.gov.pa.cosanpa.api.repository.imovel

import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.TestPropertySource


@DataJpaTest
@TestPropertySource(locations = ["/application.yml"])
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ImovelRepositoryTest {

    @Autowired
    private lateinit var repo: ImovelRepository

    @Test
    fun `dado uma matricula, deve retornar uma instancia ImovelView com id e ConsumoTarifaId`() {
        val matricula = 7389353
        val imovelView = repo.pesquisarConsumoTarifa(matricula)

        assertNotNull(imovelView)
        assertNotNull(imovelView.id)
        assertNotNull(imovelView.consumoTarifa)
        assertNull(imovelView.imovelPerfil)
    }

    @Test
    fun `dado uma matricula, deve retornar uma instancia ImovelView com atributos`() {
        val matricula = 7389353
        val imovel = repo.pesquisarEnderecoFormatado(matricula)

        assertNotNull(imovel)
    }
}