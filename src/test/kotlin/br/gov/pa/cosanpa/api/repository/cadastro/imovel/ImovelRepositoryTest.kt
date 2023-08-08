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
    fun preparacao() {
        matricula = 7389353
    }

    @Test
    fun `dado uma matricula de imovel, deve retornar uma instancia ImovelView com id e ConsumoTarifaId`() {
        val idConsumoTarifa = repo.obterIdConsumoTarifaPorImovel(matricula)

        assertNotNull(idConsumoTarifa)
        assertEquals(1, idConsumoTarifa)
    }

    @Test
    fun `dado uma matricula de imovel, deve retornar dados de endereco`() {
        val enderecoDTO = repo.obterDadosEndereco(8378878)
        assertNotNull(enderecoDTO)
        assertEquals(
            "AVENIDA PEDRO MIRANDA NUMERO 729 CA 006 FUNDOS PEDREIRA 66085-021, ENTRE TV VILETA E HUMAITA",
            enderecoDTO.formatarEndereco()
        )
    }

    @Test
    fun `dado uma matricula de imovel, entao retorna lista de dtos de categorias do imovel`() {
        val lista = repo.obterColecaoDadosCategoriasPorImovel(matricula)

        assertNotNull(lista)
        assertFalse(lista.isEmpty())
        println(lista)
    }

    @Test
    fun `dado uma matricula de imovel, entao retorna Dto com dados de Inscricao`() {
        val dto = repo.obterDadosInscricao(2658984)
        assertNotNull(dto)

        val inscricao = dto.formatarInscricao()
        assertEquals("00100819010080000", inscricao)
    }

    @Test
    fun `dado uma matricula de imovel, entao retorna lista de dtos de subcategorias do imovel`() {
        val lista = repo.obterColecaoDadosSubcategoriasPorImovel(matricula)

        assertNotNull(lista)
        assertFalse(lista.isEmpty())
        println(lista)
    }

    @Test
    fun `dado um id de rota , entao retorna ulista de ids de imoveis`() {
        //Rota com Id 2026 possui 21 Imóveis
        val dtoList = repo.obterColecaoIdsImoveis(2026)

        assertNotNull(dtoList)
        assertEquals(21, dtoList.size)
    }

    @Test
    fun `dado um id de imovel, entao retorna um Dto com informacoes relevantes clientes imoveis`() {
        val dtoList = repo.obterColecaoClienteImoveis(2096889)
        assertNotNull(dtoList)
        println(dtoList)
    }

    @Test
    fun `dado um id de imovel, entao retorna um Dto com informacoes relevantes do imovel`() {
        val dto = repo.obterDadosImovelGerarDados(2096889)
        assertNotNull(dto)
        println(dto)
    }

    @Test
    fun `dado um id de ImovelContaEnvio, entao retorna id e descricao`() {
        val dto = repo.obterDadosImovelContaEnvio(2)
        assertNotNull(dto)
        assertEquals("ENVIAR PARA O IMÓVEL", dto.descricao)
    }

    @Test
    fun `dado um id de PocoTipo, entao retorna id e descricao`() {
        val dto = repo.obterDadosPocoTipo(1)
        assertNotNull(dto)
        assertEquals("POCO ABERTO (AMAZONAS)", dto.descricao)
    }

    @Test
    fun `dado um id de imovel, entao retorna id do grupo do imovel`() {
        val idGrupo = repo.obterGrupoFaturamentoDoImovel(8705887)
        assertNotNull(idGrupo)
        assertEquals(202, idGrupo)
    }

    @Test
    fun `dado um id de imovel valido, entao retorna id de isstema de abastecimento`() {
        val idSabs = repo.obterSistemaAbastecimentoImovel(8705887)
        assertNotNull(idSabs)
        assertEquals(1, idSabs)
    }

    @Test
    fun `dado um id de imovel invalido, entao retorna null`() {
        val idSabs = repo.obterSistemaAbastecimentoImovel(7007493)
        assertNull(idSabs)
    }

    @Test
    fun `dado a chamada do metodo, retorna lista de ids e descricao de categoria`() {
        val ids = repo.obterColecaoIdDescricaoCategoriasEmUso()
        assertNotNull(ids)
        assertFalse(ids.isEmpty())
        println(ids)
    }

    @Test
    fun `dado a chamada do metodo, retorna lista de ids de consumo tarifa em uso`() {
        val ids = repo.obterColecaoIdConsumoTarifaEmUso()
        assertNotNull(ids)
        assertFalse(ids.isEmpty())
        println(ids)
    }
}