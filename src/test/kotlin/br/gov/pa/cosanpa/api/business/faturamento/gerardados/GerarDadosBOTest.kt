package br.gov.pa.cosanpa.api.business.faturamento.gerardados

import br.gov.pa.cosanpa.api.service.cadastro.imovel.ImovelService
import org.junit.jupiter.api.Assertions.*
import org.springframework.beans.factory.annotation.Autowired

class GerarDadosBOTest {

    @Autowired
    private lateinit var imovelService: ImovelService
}