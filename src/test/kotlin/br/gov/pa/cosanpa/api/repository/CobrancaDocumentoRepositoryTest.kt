package br.gov.pa.cosanpa.api.repository

import br.gov.pa.cosanpa.api.dominio.cobranca.CobrancaDebitoSituacao
import br.gov.pa.cosanpa.api.dominio.cobranca.DocumentoTipo
import br.gov.pa.cosanpa.api.repository.cobranca.CobrancaDocumentoRepository
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.TestPropertySource
import java.sql.Timestamp
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

@DataJpaTest
@TestPropertySource(locations = ["/application.yml"])
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class CobrancaDocumentoRepositoryTest {

    @Autowired
    private lateinit var repo: CobrancaDocumentoRepository

    @Test
    fun `dado os parametros corretos, retorna dto de cobrancaDocumento`() {
        val dto = repo.obterCobrancaDocumentoImpressaoSimultanea(
            2452162,
            DocumentoTipo.AVISO_CORTE,
            CobrancaDebitoSituacao.PENDENTE,
            Timestamp.valueOf("2022-12-08 07:56:22.796")
        )

        assertNotNull(dto)
    }
}