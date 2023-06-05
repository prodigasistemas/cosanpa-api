package br.gov.pa.cosanpa.api.repository.micromedicao.consumohistorico

import br.gov.pa.cosanpa.api.repository.atendimentopublico.ligacaoagua.LigacaoRepository
import br.gov.pa.cosanpa.api.repository.cadastro.imovel.ImovelRepository
import br.gov.pa.cosanpa.api.repository.micromedicao.medicao.MedicaoHistoricoRepository
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.TestPropertySource

@DataJpaTest
@TestPropertySource(locations = ["/application.yml"])
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ConsumoHistoricoRepositoryTest {

    @Autowired
    private lateinit var repo: ConsumoHistoricoRepository

    @Autowired
    private lateinit var imovelRepository: ImovelRepository

    @Autowired
    private lateinit var ligacaoRepository: LigacaoRepository

    @Test
    fun `dado uma matricula, deve retornar lista de consumo historico`() {
        val matricula = 7389353
        val lista = repo.obterConsumosEntreReferencias(
            idImovel = matricula,
            idLigacao = 1,
            amReferenciaInicial = 202209,
            amReferenciaFinal = 202212
        )

        assertNotNull(lista)
        assertEquals(4, lista.size)
        print(lista)
    }

    @Test
    fun `dado uma matricula, deve retornar ultimos seis dados de consumo historico`() {
        val matricula = 7389353
        val lista = repo.obterColecaoUltimosConsumos(idImovel = matricula, idLigacaoTipo = 1)

        assertNotNull(lista)
        assertEquals(6, lista.size)
        print(lista)
    }

}