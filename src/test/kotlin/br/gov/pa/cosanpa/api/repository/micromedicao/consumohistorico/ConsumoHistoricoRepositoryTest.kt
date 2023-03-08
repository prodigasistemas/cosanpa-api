package br.gov.pa.cosanpa.api.repository.micromedicao.consumohistorico

import br.gov.pa.cosanpa.api.repository.micromedicao.consumohistorico.ConsumoHistoricoRepository
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
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

    @Test
    fun `dado uma matricula, deve retornar lista de consumo historico`() {
        val matricula = 7389353
        val lista = repo.obterVolumeMedioAguaEsgoto(
            idImovel = matricula,
            idLigacao = 1,
            amReferenciaInicial = 202209,
            amReferenciaFinal = 202212
        )

        assertNotNull(lista)
        assertEquals(3, lista.size)
        print(lista)
    }
}