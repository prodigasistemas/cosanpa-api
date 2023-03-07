package br.gov.pa.cosanpa.api.view.imovel

import br.gov.pa.cosanpa.api.dominio.atendimento_publico.LigacaoAguaSituacao
import br.gov.pa.cosanpa.api.dominio.atendimento_publico.LigacaoEsgotoSituacao
import br.gov.pa.cosanpa.api.dominio.cadastro.cliente.ClienteImovel
import br.gov.pa.cosanpa.api.dominio.cadastro.endereco.EnderecoReferencia
import br.gov.pa.cosanpa.api.dominio.cadastro.endereco.Logradouro
import br.gov.pa.cosanpa.api.dominio.cadastro.endereco.LogradouroBairro
import br.gov.pa.cosanpa.api.dominio.cadastro.endereco.LogradouroCep
import br.gov.pa.cosanpa.api.dominio.cadastro.imovel.ImovelPerfil
import br.gov.pa.cosanpa.api.dominio.cadastro.localidade.Localidade
import br.gov.pa.cosanpa.api.dominio.cadastro.localidade.Quadra
import br.gov.pa.cosanpa.api.dominio.cadastro.localidade.SetorComercial
import br.gov.pa.cosanpa.api.dominio.faturamento.Conta
import br.gov.pa.cosanpa.api.dominio.micromedicao.consumo.ConsumoTarifa
import jakarta.persistence.*

data class ImovelView(
    val id: Int? = null,
    val lote: Int? = null,
    val sublote: Int? = null,
    val numero: String? = null,
    val complementoEndereco: String? = null,
    val idImovelCondominio: Int? = null,
    val indicadorImovelCondominio: Int? = null,
    val imovelPerfil: Int? = null,
    val localidade: Int? = null,
    val quadra: Int? = null,
    val setorComercial: Int? = null,
    val ligacaoAguaSituacao: Int? = null,
    val ligacaoEsgotoSituacao: Int? = null,
    val logradouroCep: Int? = null,
    val logradouroBairro: Int? = null,
    val enderecoReferencia: Int? = null,
    val perimetroInicial: Int? = null,
    val perimetroFinal: Int? = null,
    val consumoTarifa: Int? = null

)

