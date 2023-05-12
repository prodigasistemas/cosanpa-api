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
        assertNotNull(imovelDTO.idConsumoTarifa)
        assertNull(imovelDTO.idImovelPerfil)
    }

    @Test
    fun `dado uma matricula de imovel, deve retornar uma instancia ImovelView com atributos`() {
        val enderecoDTO = repo.obterDadosEndereco(2326426)
        assertNotNull(enderecoDTO)
        println(enderecoDTO.formatarEndereco())
    }

    @Test
    fun `dado uma matricula de imovel, entao retorna lista de dtos de categorias do imovel`(){
        val lista = repo.obterDadosCategoriasPorImovel(matricula)

        assertNotNull(lista)
        assertFalse(lista.isEmpty())
        println(lista)
    }

    @Test
    fun `dado uma matricula de imovel, entao retorna DTO com dados de Inscricao`(){
        val dto = repo.obterDadosInscricao(2658984)
        assertNotNull(dto)

        val inscricao = dto.formatarInscricao()
        assertEquals("00100819010080000", inscricao)
    }

    @Test
    fun `dado uma matricula de imovel, entao retorna lista de dtos de subcategorias do imovel`(){
        val lista = repo.obterDadosSubcategoriasPorImovel(matricula)

        assertNotNull(lista)
        assertFalse(lista.isEmpty())
        println(lista)
    }

    @Test
    fun `dado um id de rota , entao retorna ulista de ids de imoveis`() {
        //Rota com Id 2026 possui 21 Imóveis
        val dtoList = repo.obterIdsImoveis(2026)
        
        assertNotNull(dtoList)
        assertEquals(21, dtoList.size)
    }

    @Test
    fun `dado um id de imovel, entao retorna um DTO com informacoes relevantes clientes imoveis`() {
        val dtoList = repo.obterClienteImoveis( 2096889)
        assertNotNull(dtoList)
        println(dtoList)
    }

    @Test
    fun `dado um id de imovel, entao retorna um DTO com informacoes relevantes do imovel`() {
        val dto = repo.obterDadosImovelGerarDados( 2096889)
        assertNotNull(dto)
        println(dto)
    }

    @Test
    fun `dado um id de ImovelContaEnvio, entao retorna id e descricao`() {
        val dto = repo.obterDadosImovelContaEnvio(2)
        assertNotNull(dto)
        assertEquals("ENVIAR PARA O IMÓVEL", dto.descricao)
    }
}