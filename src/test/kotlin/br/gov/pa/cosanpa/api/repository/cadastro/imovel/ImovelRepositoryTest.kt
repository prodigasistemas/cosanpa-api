package br.gov.pa.cosanpa.api.repository.cadastro.imovel

import br.gov.pa.cosanpa.api.repository.cadastro.imovel.ImovelRepository
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
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

    var matricula: Int = 0


    @BeforeEach
    fun preparacao(){
        matricula = 7389353
    }

    @Test
    fun `dado uma matricula de imovel, deve retornar uma instancia ImovelView com id e ConsumoTarifaId`() {
        val imovelDTO = repo.obterConsumoTarifa(matricula)

        assertNotNull(imovelDTO)
        assertNotNull(imovelDTO.id)
        assertNotNull(imovelDTO.consumoTarifa)
        assertNull(imovelDTO.imovelPerfil)
    }

    @Test
    fun `dado uma matricula de imovel, deve retornar uma instancia ImovelView com atributos`() {
        val imovel = repo.obterDadosEndereco(matricula)
        assertNotNull(imovel)
    }

    @Test
    fun `dado uma matricula de imovel, entao retorna lista de categorias do imovel`(){
        val lista = repo.obterCategoriasPorImovel(matricula)

        assertNotNull(lista)
        assertFalse(lista.isEmpty())
        print(lista)
    }
}