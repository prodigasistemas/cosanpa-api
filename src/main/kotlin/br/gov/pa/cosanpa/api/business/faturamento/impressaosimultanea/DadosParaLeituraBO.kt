package br.gov.pa.cosanpa.api.business.faturamento.impressaosimultanea

import br.gov.pa.cosanpa.api.business.micromedicao.consumo.ConsumoMinimoLigacaoBO
import br.gov.pa.cosanpa.api.business.micromedicao.consumo.MediaConsumoAguaEsgotoBO
import br.gov.pa.cosanpa.api.business.micromedicao.leitura.CalcularFaixaLeituraEsperadaBO
import br.gov.pa.cosanpa.api.dominio.cadastro.cliente.ClienteRelacaoTipo
import br.gov.pa.cosanpa.api.dominio.cadastro.imovel.ImovelContaEnvio
import br.gov.pa.cosanpa.api.dto.cadastro.cliente.ClienteImovelContaDTO
import br.gov.pa.cosanpa.api.dto.cadastro.imovel.ImovelDTO
import br.gov.pa.cosanpa.api.dto.faturamento.debitocredito.DebitoCobradoDTO
import br.gov.pa.cosanpa.api.dto.micromedicao.hidrometro.HidrometroDTO
import br.gov.pa.cosanpa.api.dto.micromedicao.hidrometro.HidrometroInstalacaoHistoricoDTO
import br.gov.pa.cosanpa.api.extensions.util.converterReferenciaParaLocalDate
import br.gov.pa.cosanpa.api.extensions.util.conveterLocalDateParaReferencia
import br.gov.pa.cosanpa.api.service.arrecadacao.banco.BancoService
import br.gov.pa.cosanpa.api.service.arrecadacao.debitoautomatico.DebitoAutomaticoService
import br.gov.pa.cosanpa.api.service.atendimentopublico.LigacaoService
import br.gov.pa.cosanpa.api.service.cadastro.SistemaParametrosService
import br.gov.pa.cosanpa.api.service.cadastro.cliente.ClienteService
import br.gov.pa.cosanpa.api.service.cadastro.endereco.EnderecoService
import br.gov.pa.cosanpa.api.service.cadastro.imovel.CategoriaService
import br.gov.pa.cosanpa.api.service.cadastro.imovel.ImovelService
import br.gov.pa.cosanpa.api.service.cadastro.imovel.localidade.LocalidadeService
import br.gov.pa.cosanpa.api.service.cobranca.CobrancaDocumentoService
import br.gov.pa.cosanpa.api.service.faturamento.ExtratoQuitacaoService
import br.gov.pa.cosanpa.api.service.faturamento.consumo.ConsumoTarifaService
import br.gov.pa.cosanpa.api.service.faturamento.conta.ContaService
import br.gov.pa.cosanpa.api.service.faturamento.credito.CreditoService
import br.gov.pa.cosanpa.api.service.faturamento.debito.DebitoService
import br.gov.pa.cosanpa.api.service.faturamento.qualidadeagua.QualidadeAguaService
import br.gov.pa.cosanpa.api.service.faturamento.situacao.FaturamentoSituacaoService
import br.gov.pa.cosanpa.api.service.micromedicao.HidrometroService
import br.gov.pa.cosanpa.api.service.micromedicao.consumo.ConsumoHistoricoService
import br.gov.pa.cosanpa.api.service.micromedicao.leitura.anormalidade.LeituraAnormalidadeService
import br.gov.pa.cosanpa.api.service.micromedicao.medicao.MedicaoHistoricoService
import br.gov.pa.cosanpa.api.util.ConstantesSistema
import br.gov.pa.cosanpa.api.view.DadosLeituraView
import org.springframework.stereotype.Component
import java.time.LocalDateTime

@Component
class DadosParaLeituraBO(
    private val imovelService: ImovelService,
    private val contaService: ContaService,
    private val localidadeService: LocalidadeService,
    private val categoriaService: CategoriaService,
    private val enderecoService: EnderecoService,
    private val clienteService: ClienteService,
    private val ligacaoService: LigacaoService,
    private val debitoAutomaticoService: DebitoAutomaticoService,
    private val bancoService: BancoService,
    private val faturamentoSituacaoService: FaturamentoSituacaoService,
    private val leituraAnormalidadeService: LeituraAnormalidadeService,
    private val consumoHistoricoService: ConsumoHistoricoService,
    private val medicaoHistoricoService: MedicaoHistoricoService,
    private val hidrometroService: HidrometroService,
    private val debitoService: DebitoService,
    private val creditoService: CreditoService,
    private val consumoTarifaService: ConsumoTarifaService,
    private val extratoQuitacaoService: ExtratoQuitacaoService,
    private val qualidadeAguaService: QualidadeAguaService,
    private val cobrancaDocumentoService: CobrancaDocumentoService,
    private val sistemaParametrosService: SistemaParametrosService
) {
    private val gerarDados = DadosLeituraView(mutableMapOf())
    private val referenciaFaturamento = sistemaParametrosService.obterParametrosDoSistema().referenciaFaturamento

    fun obterPorImovel(idImovel: Int): DadosLeituraView {
        adicionarDadosPorImovel(idImovel)
        return gerarDados
    }

    private fun adicionarDadoAoMap(key: String, value: Any) {
        gerarDados.dadosImovel[key] = value
    }

    private fun adicionarDadosPorImovel(idImovel: Int) {
        val imovelDto = imovelService.obterImovelGerarDados(idImovel)
        imovelDto?.run {
            adicionarDadosImovel(id)
            adicionarDadosConta(id)
            adicionarEnderecoInscricao(id)
            adicionarDadosLocalidade(idLocalidade)
            adicionarDadosCategoria(id)
            processarClienteImovel(id, idImovelContaEnvio)
            processarDadosLigacao(id, idLigacaoAguaSituacao, idLigacaoEsgotoSituacao)
            adicionarDadosConsumoTarifa(idConsumoTarifa)
            adicionarDadosImovelContaEnvio(idImovelContaEnvio)
            adicionarDadosPocoTipo(idPocoTipo)
            processarDebitoAutomatico(id)
            processarDadosMensagemConta(this)
            adicionarDadosQualidadeAqua(this)
            processarDadosFaturamentoSituacao(this)
            adicionarDadosConsumoHistorico(this)
            adicionarDadosDebitos(id)
            adicionarDadosCreditos(id)
            adicionarDadosContaImpostosDeduzidos(id)
            processarDadosCobrancaDocumentoItem(id)
            adicionarDadosMedicaoHistorico(id)
            adicionarDadosHidrometro(id)
        }
    }

    private fun obterGrupoDoImovel(idImovel: Int) = imovelService.obterGrupo(idImovel)

    private fun adicionarDadosImovel(idImovel: Int?) {
        idImovel?.let {
            imovelService.obterImovelViewGerarDados(it)?.let { imovelView ->
                adicionarDadoAoMap("imovel", imovelView)
            }
        }
    }

    private fun adicionarEnderecoInscricao(idImovel: Int?) {
        idImovel?.let {
            val endereco = enderecoService.obterEnderecoFormatadoImovel(idImovel)
            adicionarDadoAoMap("endereco", endereco)
            val inscricao = imovelService.obterDadosInscricao(idImovel)
            adicionarDadoAoMap("inscricao", inscricao)
        }
    }

    private fun adicionarDadosConta(idImovel: Int?) {
        idImovel?.let {
            contaService.obterDadosContaPreFaturadaView(it, referenciaFaturamento)?.let { contaView ->
                adicionarDadoAoMap("conta", contaView)
            } ?: adicionarDadoAoMap("conta", "")
        }
    }

    private fun adicionarDadosCategoria(idImovel: Int?) {
        idImovel?.let {
            adicionarDadoAoMap("categorias", categoriaService.obterCategoriasView(it))
        }
    }

    private fun adicionarDadosLocalidade(idLocalidade: Int?) {
        idLocalidade?.let {
            adicionarDadoAoMap("localidade", localidadeService.obterLocalidadeView(it))
            adicionarDadosGerencialRegional(it)
            adicionarDadosEnderecoAtendimento(it)
            adicionarDadosFoneAtendimento(it)
        }
    }

    private fun adicionarDadosGerencialRegional(idLocalidade: Int) {
        localidadeService.obterDadosLocalidade(idLocalidade).idGerenciaRegional?.let { idGerenciaRegional ->
            val gerenciaRegionalView = localidadeService.obterGerenciaRegionalView(idGerenciaRegional)
            adicionarDadoAoMap("gerenciaRegional", gerenciaRegionalView)
        }
    }

    private fun adicionarDadosEnderecoAtendimento(idLocalidade: Int) {
        val enderecoAtendimentoLocalidade = enderecoService.obterEnderecoAtendimentoLocalidade(idLocalidade)
        if (enderecoAtendimentoLocalidade != "null") adicionarDadoAoMap(
            "enderecoAtendimento",
            enderecoAtendimentoLocalidade
        )
        else adicionarDadoAoMap("enderecoAtendimento", "")
    }

    private fun adicionarDadosFoneAtendimento(idLocalidade: Int) {
        val mapFone = mutableMapOf<String, Any>()
        localidadeService.obterFoneAtendimento(idLocalidade)?.let { numero ->
            mapFone["ddd"] = localidadeService.obterDDDMunicipioPorLocalidade(idLocalidade) ?: ""
            mapFone["numero"] = numero
            adicionarDadoAoMap("foneAtendimento", mapFone)
        } ?: adicionarDadoAoMap("foneAtendimento", "")
    }

    private fun processarClienteImovel(idImovel: Int?, idImovelContaEnvio: Int?) {
        idImovel?.let {
            imovelService.obterDadosClienteImovel(it)
                .filter { clienteImovel -> clienteImovel.indicadorNomeConta == ConstantesSistema.SIM }
                .forEach { clienteImovelContaDTO ->
                    adicionarDadosCliente(clienteImovelContaDTO, idImovelContaEnvio)
                }
        }
    }

    private fun adicionarDadosCliente(
        clienteImovelContaDTO: ClienteImovelContaDTO,
        idImovelContaEnvio: Int?
    ) {
        clienteImovelContaDTO.idCliente?.let { idCliente ->
            val clienteView = clienteService.obterClienteView(idCliente)

            clienteImovelContaDTO.idClienteRelacaoTipo?.let { idRelacaoTipo ->
                if (idRelacaoTipo == ClienteRelacaoTipo.USUARIO) {
                    adicionarDadoAoMap("clienteUsuario", clienteView)
                } else {
                    adicionarDadoAoMap("clienteResponsavel", clienteView)
                    idImovelContaEnvio?.let {
                        if (it == ImovelContaEnvio.ENVIAR_IMOVEL) {
                            adicionarDadoAoMap(
                                "enderecoCorrespondencia",
                                clienteService.obterEnderecoCliente(idCliente)
                            )
                        }
                    }
                }
            }
        }
    }

    private fun processarDadosLigacao(idImovel: Int?, idLigacaoAguaSituacao: Int?, idLigacaoEsgotoSituacao: Int?) {
        adicionarDadosLigacaoAguaSituacao(idLigacaoAguaSituacao)
        adicionarDadosLigacaoEsgotoSituacao(idLigacaoEsgotoSituacao)
        idImovel?.let {
            adicionarDadosLigacaoAguaEsgoto(it)
        }
    }

    private fun adicionarDadosLigacaoAguaEsgoto(idImovel: Int) {
        ligacaoService.obterLigacaoAguaView(idImovel)?.let { ligacaoAguaView ->
            adicionarDadoAoMap("ligacaoAgua", ligacaoAguaView)
        }
        ligacaoService.obterLigacaoEsgotoView(idImovel)?.let { ligacaoEsgotoView ->
            adicionarDadoAoMap("ligacaoEsgoto", ligacaoEsgotoView)
        }
    }

    private fun adicionarDadosLigacaoAguaSituacao(idLigacaoAguaSituacao: Int?) {
        idLigacaoAguaSituacao?.let {
            adicionarDadoAoMap(
                "ligacaoAguaSituacao",
                ligacaoService.obterLigacaoAguaSituacaoView(it)
            )
        }
    }

    private fun adicionarDadosLigacaoEsgotoSituacao(idLigacaoEsgotoSituacao: Int?) {
        idLigacaoEsgotoSituacao?.let {
            adicionarDadoAoMap(
                "ligacaoEsgotoSituacao",
                ligacaoService.obterLigacaoEsgotoSituacaoView(it)
            )
        }
    }

    private fun adicionarDadosConsumoTarifa(idConsumoTarifa: Int?) {
        idConsumoTarifa?.let {
            adicionarDadoAoMap("consumoTarifa", consumoTarifaService.obterDadosConsumoTarifaView(it))
        } ?: adicionarDadoAoMap("consumoTarifa", "")
    }

    private fun adicionarDadosImovelContaEnvio(idImovelContaEnvio: Int?) {
        idImovelContaEnvio?.let {
            adicionarDadoAoMap("imovelContaEnvio", imovelService.obterImovelContaEnvioView(it))
        } ?: adicionarDadoAoMap("imovelContaEnvio", "")
    }

    private fun adicionarDadosPocoTipo(idPocoTipo: Int?) {
        idPocoTipo?.let {
            adicionarDadoAoMap("pocoTipo", imovelService.obterPocoTipoView(it))
        } ?: adicionarDadoAoMap("pocoTipo", "")
    }

    private fun processarDebitoAutomatico(idImovel: Int?) {
        idImovel?.let {
            debitoAutomaticoService.obterDadosDebitoAutomaticoPorImovelId(it)?.idAgencia?.let { idAgencia ->
                adicionarDadoAoMap("dadosDebitoAutomatico", retornaDadosBancoAgencia(idAgencia))
            } ?: adicionarDadoAoMap("dadosDebitoAutomatico", "")
        }
    }

    private fun retornaDadosBancoAgencia(idAgencia: Int?): Map<String, Any> {
        val mapBancoAgencia = mutableMapOf<String, Any>()
        idAgencia?.let {
            bancoService.obterDadosAgencia(it).let { agenciaDTO ->
                agenciaDTO.idBanco?.let { idBanco ->
                    mapBancoAgencia["banco"] = bancoService.obterBancoView(idBanco)
                }

                agenciaDTO.id?.let { idAgencia ->
                    mapBancoAgencia["agencia"] = bancoService.obterAgenciaView(idAgencia)
                }
            }
        }
        return mapBancoAgencia
    }

    private fun processarDadosMensagemConta(imovelDto: ImovelDTO) {
        imovelDto.id?.let {
            if (contaService.possuiComunicadoNaoEmitidoAlteracaoCadastral(it, referenciaFaturamento)) {
                adicionarDadoAoMap(
                    "mensagemConta",
                    "Imovel recadastrado, carta de comunicacao anteriormente enviada ao usuario pelos correios."
                )
            } else {
                adicionarMensagemQuitacaoDebitos(it)
                adicionarMensagemConta3Partes(it, imovelDto)
            }
        }
    }

    private fun adicionarMensagemQuitacaoDebitos(idImovel: Int) {
        extratoQuitacaoService.obterMensagemExtratoQuitacaoImovel(idImovel, referenciaFaturamento)
            ?.let { mensagemQuitacaoDebitos ->
                gerarDados.dadosImovel.putIfAbsent("mensagemConta", mensagemQuitacaoDebitos)
            }
    }

    private fun adicionarMensagemConta3Partes(
        idImovel: Int,
        imovelDto: ImovelDTO,
    ) {
        val grupoFaturamento = obterGrupoDoImovel(idImovel)
        val localidadeDTO = imovelDto.idLocalidade?.let { localidadeService.obterDadosLocalidade(it) }

        val mensagem = contaService.obterMensagemContaView(
            referenciaFaturamento,
            grupoFaturamento,
            localidadeDTO?.idGerenciaRegional,
            imovelDto.idLocalidade,
            imovelDto.idSetorComercial,
            imovelDto.idQuadra
        )

        if (mensagem != null) gerarDados.dadosImovel.putIfAbsent("mensagemConta", mensagem)
        else gerarDados.dadosImovel.putIfAbsent("mensagemConta", "")
    }

    private fun adicionarDadosQualidadeAqua(imovelDto: ImovelDTO) {
        val idSistemaAbastecimento = imovelDto.id?.let { imovelService.obterSistemaAbastecimento(it) }

        val map = mutableMapOf<String, Any>()

        map["padrao"] = qualidadeAguaService.obterQualidadeAguaPadraoView()
        qualidadeAguaService.obterDadosQualidadeAgua(
            referencia = referenciaFaturamento,
            idLocalidade = imovelDto.idLocalidade,
            idSetorComercial = imovelDto.idSetorComercial,
            idSistemaAbastecimento = idSistemaAbastecimento
        )?.let {
            map["qualidadeAguaImovel"] = it
        }

        adicionarDadoAoMap("qualidadeAgua", map)
    }

    private fun processarDadosFaturamentoSituacao(imovelDto: ImovelDTO) {
        val map = mutableMapOf<String, Any>()
        imovelDto.id?.let { idImovel ->
            adicionarDadosFaturamentoSituacaoHistorico(idImovel, map)
            adicionarFaturamentoSituacaoTipoView(imovelDto, map)
        }
        adicionarDadoAoMap("faturamentoSituacao", map)
    }

    private fun adicionarDadosFaturamentoSituacaoHistorico(idImovel: Int, map: MutableMap<String, Any>) {
        faturamentoSituacaoService.obterDadosHistoricoPorImovelSemRetiradaView(idImovel, referenciaFaturamento)
            ?.let { historicoView ->
                map["historico"] = historicoView
            }
    }

    private fun adicionarFaturamentoSituacaoTipoView(imovelDto: ImovelDTO, map: MutableMap<String, Any>) {
        imovelDto.idFaturamentoSituacaoTipo?.let {
            faturamentoSituacaoService.obterTipoView(it)?.let { tipoView ->
                map["tipo"] = tipoView
            }
            adicionarDadosLeituraAnormalidade(it, map)
        }
    }

    private fun adicionarDadosLeituraAnormalidade(idFaturamentoSituacaoTipo: Int, map: MutableMap<String, Any>) {
        faturamentoSituacaoService.obterDadosTipo(idFaturamentoSituacaoTipo)?.run {
            idLeituraAnormalidadeLeituraComLeitura?.let {
                map["leituraAnormalidadeLeituraComLeitura"] =
                    leituraAnormalidadeService.obterLeituraAnormalidadeLeituraView(it)
            }
            idLeituraAnormalidadeLeituraSemLeitura?.let {
                map["leituraAnormalidadeLeituraSemLeitura"] =
                    leituraAnormalidadeService.obterLeituraAnormalidadeLeituraView(it)
            }
            idLeituraAnormalidadeConsumoComLeitura?.let {
                map["leituraAnormalidadeConsumoComLeitura"] =
                    leituraAnormalidadeService.obterLeituraAnormalidadeConsumoView(it)
            }
            idLeituraAnormalidadeConsumoSemLeitura?.let {
                map["leituraAnormalidadeConsumoSemLeitura"] =
                    leituraAnormalidadeService.obterLeituraAnormalidadeConsumoView(it)
            }
        }
    }

    private fun adicionarDadosConsumoHistorico(imovelDto: ImovelDTO) {
        val mapReferencia = mutableMapOf<Int, Any>()
        imovelDto.id?.run {

            val ligacaoAguaDTO = ligacaoService.obterDadosLigacaoAgua(this)

            val medicaoHistoricoDTO = medicaoHistoricoService.obterDadosMedicaoHistorico(this, referenciaFaturamento)

            val colecaoUltimosConsumosHistorico = consumoHistoricoService.obterColecaoUltimosConsumosHistorico(
                this,
                ligacaoAguaDTO?.idHidrometroInstalacaoHistorico
            )
            colecaoUltimosConsumosHistorico.forEach { consumoHistoricoDTO ->
                val mapConsumo = mutableMapOf<String, Any>()

                mapConsumo["historico"] = consumoHistoricoService.obterColecaoViewConsumosHistorico(consumoHistoricoDTO)
                mapConsumo["anormalidade"] = consumoHistoricoDTO.idConsumoAnormalidade?.let {
                    consumoHistoricoService.obterConsumoAnormalidadeView(it)
                } ?: ""
                mapConsumo["leituraAnormalidade"] = medicaoHistoricoDTO?.idLeituraAnormalidadeFaturamento?.let {
                    leituraAnormalidadeService.obterLeituraAnormalidadeView(it)
                } ?: ""

                consumoHistoricoDTO.referencia?.let {
                    mapReferencia[it] = mapConsumo
                }
            }
        }
        adicionarDadoAoMap("consumos", mapReferencia)
    }

    private fun adicionarDadosDebitos(idImovel: Int?) {
        val parcelamentos = mutableListOf<Map<String, Any>>()
        val naoParcelamentos = mutableListOf<Map<String, Any>>()
        var map: MutableMap<String, Any>
        val contaDto = idImovel?.let { contaService.obterContaPreFaturadaPorImovelId(it, 202303) }

        contaDto?.id?.let {
            debitoService.obterColecaoDebitosCobradosParcelamento(it).forEach { debitoCobradoDTO ->
                map = retornaMapDeDebitos(debitoCobradoDTO)
                parcelamentos.add(map)
            }

            debitoService.obterColecaoDebitosCobradosNaoParcelamento(it).forEach { debitoCobradoDTO ->
                map = retornaMapDeDebitos(debitoCobradoDTO)
                naoParcelamentos.add(map)
            }
        }
        map = mutableMapOf()
        if (parcelamentos.isNotEmpty()) map["parcelamentos"] = parcelamentos
        if (naoParcelamentos.isNotEmpty()) map["naoParcelamentos"] = naoParcelamentos

        adicionarDadoAoMap("debitos", map)
    }

    private fun retornaMapDeDebitos(
        debitoCobradoDTO: DebitoCobradoDTO
    ): MutableMap<String, Any> {
        val map = mutableMapOf<String, Any>()
        map["debitoCobrado"] = debitoService.obterDebitosCobradoView(debitoCobradoDTO)
        map["debitoTipo"] = debitoCobradoDTO.idDebitoTipo?.let { it1 -> debitoService.obterDebitoTipoView(it1) } ?: ""
        map["financiamentoTipo"] =
            debitoCobradoDTO.idFinanciamentoTipo?.let { it2 -> debitoService.obterFinanciamentoTipoView(it2) } ?: ""
        return map
    }

    private fun adicionarDadosCreditos(idImovel: Int?) {
        var map = mutableMapOf<String, Any>()
        val contaDto = idImovel?.let { contaService.obterContaPreFaturadaPorImovelId(it, 202303) }

        contaDto?.id?.let {
            creditoService.obterColecaoCreditosRealizados(it).forEach { creditoRealizadoDTO ->
                map = mutableMapOf()
                map["creditoRealizado"] = creditoService.obterColecaoCreditosRealizadosView(creditoRealizadoDTO)
                map["creditoTipo"] =
                    creditoRealizadoDTO.idCreditoTipo?.let { it1 -> creditoService.obterCreditoTipoView(it1) } ?: ""
            }
        }
        adicionarDadoAoMap("creditos", map)
    }

    private fun adicionarDadosContaImpostosDeduzidos(idImovel: Int?) {
        var map = mutableMapOf<String, Any>()
        val contaDto = idImovel?.let { contaService.obterContaPreFaturadaPorImovelId(it, 202303) }

        contaDto?.id?.let {
            contaService.obterColecaoContaImpostoDeduzidos(it).forEach { contaImpostosDeduzidosDTO ->
                map = mutableMapOf()
                map["impostoDeduzido"] = contaService.obterContaImpostosDeduzidosView(contaImpostosDeduzidosDTO)
                map["impostoTipo"] =
                    contaImpostosDeduzidosDTO.idImpostoTipo?.let { it1 -> contaService.obterImpostoTipoView(it1) } ?: ""
            }
        }
        adicionarDadoAoMap("impostos", map)
    }

    private fun processarDadosCobrancaDocumentoItem(idImovel: Int?) {
        var map = mutableMapOf<String, Any>()
        idImovel?.let {
            val dataEmissao = LocalDateTime.now().minusDays(10)
            cobrancaDocumentoService.obterDadosCobrancaDocumentoAvisoCorte(it, dataEmissao)?.run {
                this.id?.let { idCobrancaDocumento ->
                    cobrancaDocumentoService.obterColecaoCobrancaDocumentoItem(idCobrancaDocumento)
                        .forEach { cobrancaDocumentoItemDTO ->
                            map = mutableMapOf()
                            map["documentoItem"] =
                                cobrancaDocumentoService.obterCobrancaDocumentoItemView(cobrancaDocumentoItemDTO)
                            map["documentoTipo"] = this.idDocumentoTipo?.let { idDocTipo ->
                                cobrancaDocumentoService.obterDocumentoTipoView(idDocTipo)
                            } ?: ""
                        }
                }
            }
        }

        adicionarDadoAoMap("documentosCobranca", map)
    }

    private fun adicionarDadosMedicaoHistorico(idImovel: Int?) {
        var map = mutableMapOf<String, Any>()
        idImovel?.let {
            map = retornarMapDeMedicao(it, referenciaFaturamento)

            if (map.isEmpty()) {
                val referenciaAnterior = referenciaFaturamento
                    .converterReferenciaParaLocalDate()
                    .minusMonths(1)
                    .conveterLocalDateParaReferencia()

                map = retornarMapDeMedicao(it, referenciaAnterior)
            }
        }
        adicionarDadoAoMap("medicao", map)
    }

    private fun retornarMapDeMedicao(idImovel: Int, referencia: Int): MutableMap<String, Any> {
        var map = mutableMapOf<String, Any>()
        medicaoHistoricoService.obterDadosMedicaoHistorico(idImovel, referencia)
            ?.let { medicaoHistoricoDTO ->
                map = mutableMapOf()
                map["medicaoHistorico"] = medicaoHistoricoService.obterMedicaoHistoricoView(medicaoHistoricoDTO)
                map["medicaoTipo"] =
                    medicaoHistoricoDTO.idMedicaoTipo?.let { medicaoHistoricoService.obterMedicaoTipoView(it) } ?: ""
            }
        return map
    }

    private fun adicionarDadosHidrometro(idImovel: Int?) {
        var map = mutableMapOf<String, Any>()
        idImovel?.let {
            hidrometroService.obterDadosHidrometroInstalacaoHistoricoPorImovel(it)
                ?.let { hidrometroInstalacaoHistoricoDTO ->
                    map = mutableMapOf()
                    map["historicoInstalacao"] =
                        hidrometroService.obterHidrometroInstalacaoHistoricoView(hidrometroInstalacaoHistoricoDTO)

                    var hidrometroDto = HidrometroDTO(null, null, null)

                    map["hidrometro"] = hidrometroInstalacaoHistoricoDTO.idHidrometro?.let { it1 ->
                        hidrometroDto = hidrometroService.obterDadosHidrometro(it1)
                        hidrometroService.obterHidrometroView(it1)
                    } ?: ""
                    map["hidrometroProtecao"] = hidrometroInstalacaoHistoricoDTO.idHidrometroProtecao?.let { it1 ->
                        hidrometroService.obterHidrometroProtecaoView(it1)
                    } ?: ""

                    map["hidrometroLocalInstalacao"] =
                        hidrometroInstalacaoHistoricoDTO.idHidrometroLocalInstalacao?.let { it1 ->
                            hidrometroService.obterHidrometroLocalInstalacaoView(it1)
                        } ?: ""

                    val mediaConsumo = obterConsumoMedioHidrometro(it, hidrometroInstalacaoHistoricoDTO)
                    map["consumoMedioHidrometro"] = mediaConsumo

                    val leituraAnteriorFaturamento = medicaoHistoricoService.obterDadosMedicaoHistorico(
                        idImovel,
                        referenciaFaturamento
                    )?.leituraAnterioFaturamento

                    map["faixaLeituraEsperada"] = CalcularFaixaLeituraEsperadaBO(
                        media = mediaConsumo,
                        hidrometroDTO = hidrometroDto,
                        medicaoHistorico = null,
                        leituraAnteriorPesquisada = leituraAnteriorFaturamento
                    ).calcular()
                }
        }
        adicionarDadoAoMap("dadosHidrometro", map)
    }


    private fun obterConsumoMedioHidrometro(
        idImovel: Int,
        hidrometroInstalacaoHistoricoDTO: HidrometroInstalacaoHistoricoDTO
    ): Int {
        return hidrometroInstalacaoHistoricoDTO.idMedicaoTipo?.let {
            MediaConsumoAguaEsgotoBO(sistemaParametrosService, consumoHistoricoService).obter(
                idImovel,
                referenciaFaturamento,
                it
            )
        } ?: ConsumoMinimoLigacaoBO(imovelService, categoriaService, consumoTarifaService).obter(idImovel)
    }
}

