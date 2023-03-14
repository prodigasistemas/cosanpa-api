package br.gov.pa.cosanpa.api.repository.cadastro.imovel

import br.gov.pa.cosanpa.api.extensions.cadastro.endereco.formatarEndereco
import br.gov.pa.cosanpa.api.extensions.cadastro.imovel.formatarInscricao
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
        val enderecoDTO = repo.obterDadosEndereco(matricula)
        assertNotNull(enderecoDTO)
        print(enderecoDTO.formatarEndereco())
    }

    @Test
    fun `dado uma matricula de imovel, entao retorna lista de categorias do imovel`(){
        val lista = repo.obterDadosCategoriasPorImovel(matricula)

        assertNotNull(lista)
        assertFalse(lista.isEmpty())
        print(lista)
    }

    @Test
    fun `dado uma matricula de imovel, entao retorna DTO com dados de Inscricao`(){
        val dto = repo.obterDadosInscricao(2658984)
        assertNotNull(dto)

        val inscricao = dto.formatarInscricao()
        assertEquals("00100819010080000", inscricao)
    }
}