package br.gov.pa.cosanpa.api.view.consumo

import br.gov.pa.cosanpa.api.dominio.cadastro.imovel.Imovel
import br.gov.pa.cosanpa.api.dominio.micromedicao.consumo.ConsumoAnormalidade
import br.gov.pa.cosanpa.api.dominio.micromedicao.consumo.ConsumoTipo
import br.gov.pa.cosanpa.api.dominio.micromedicao.consumo.LigacaoTipo
import jakarta.persistence.*

data class ConsumoHistoricoView(
    val id: Int? = null,
    val referencia: Int? = null,
    val numeroCalculoConsumoMedia: Int? = null,
    val imovel: Int? = null,
    val consumoTipo: Int? = null,
    val consumoAnormalidade: Int? = null,
    val ligacaoTipo: Int? = null
)
