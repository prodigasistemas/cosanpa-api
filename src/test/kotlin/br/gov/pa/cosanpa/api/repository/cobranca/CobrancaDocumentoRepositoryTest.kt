package br.gov.pa.cosanpa.api.repository.cobranca

import br.gov.pa.cosanpa.api.dominio.cobranca.CobrancaDebitoSituacao
import br.gov.pa.cosanpa.api.dominio.cobranca.DocumentoTipo
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.TestPropertySource
import java.sql.Timestamp

@DataJpaTest
@TestPropertySource(locations = ["/application.yml"])
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class CobrancaDocumentoRepositoryTest {

    @Autowired
    private lateinit var repo: CobrancaDocumentoRepository

    @Test
    fun `dado os parametros corretos, entao retorna dto de cobrancaDocumento`() {
        val dto = repo.obterCobrancaDocumentoImpressaoSimultanea(
            2452162,
            DocumentoTipo.AVISO_CORTE,
            CobrancaDebitoSituacao.PENDENTE,
            Timestamp.valueOf("2022-12-08 07:56:22.796")
        )

        assertNotNull(dto)
    }

    @Test
    fun `dado o id de CobrancaDocumento, entao retorna dtos de cobrancaDocumentoitem`() {
        //Cobranca Documento DE id 19149109 possui 36 registros para Id de Conta não nulo
        val dtos = repo.obterCobrancaDocumentoItemReferenteConta(19149109)
        assertNotNull(dtos)
        assertEquals(36, dtos.size)
    }
}