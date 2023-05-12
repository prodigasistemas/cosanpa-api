package br.gov.pa.cosanpa.api.business.faturamento.gerardados

import br.gov.pa.cosanpa.api.dominio.cadastro.cliente.ClienteRelacaoTipo
import br.gov.pa.cosanpa.api.dominio.cadastro.imovel.ImovelContaEnvio
import br.gov.pa.cosanpa.api.dto.cadastro.cliente.ClienteImovelContaDTO
import br.gov.pa.cosanpa.api.service.arrecadacao.banco.BancoService
import br.gov.pa.cosanpa.api.service.arrecadacao.debitoautomatico.DebitoAutomaticoService
import br.gov.pa.cosanpa.api.service.atendimentopublico.LigacaoService
import br.gov.pa.cosanpa.api.service.cadastro.cliente.ClienteService
import br.gov.pa.cosanpa.api.service.cadastro.endereco.EnderecoService
import br.gov.pa.cosanpa.api.service.cadastro.imovel.CategoriaService
import br.gov.pa.cosanpa.api.service.cadastro.imovel.ImovelService
import br.gov.pa.cosanpa.api.service.cadastro.imovel.localidade.LocalidadeService
import br.gov.pa.cosanpa.api.util.ConstantesSistema
import br.gov.pa.cosanpa.api.view.GerarDadosView
import org.springframework.stereotype.Component

@Component
class GerarDadosBO(
    private val imovelService: ImovelService,
    private val localidadeService: LocalidadeService,
    private val categoriaService: CategoriaService,
    private val enderecoService: EnderecoService,
    private val clienteService: ClienteService,
    private val ligacaoService: LigacaoService,
    private val debitoAutomaticoService: DebitoAutomaticoService,
    private val bancoService: BancoService
) {
    private val gerarDados: GerarDadosView = GerarDadosView(mutableMapOf())

    fun gerarPorImovel(idImovel: Int): GerarDadosView {
        adicionarDadosPorImovel(idImovel)
        return gerarDados
    }

    fun gerarPorConta(idConta: Int): GerarDadosView {
        adicionarDadosPorImovel(idConta)
        return gerarDados
    }

    private fun adicionarDadoAoMap(key: String, value: Any) {
        gerarDados.dados[key] = value
    }

    private fun adicionarDadosPorImovel(idImovel: Int) {
        val imovelDto = imovelService.obterDadosImovel(idImovel)
        imovelDto?.let {
            adicionarDadosImovel(it.id)
            adicionarDadosLocalidade(it.idLocalidade)
            adicionarDadosCategoria(it.id)
            processarClienteImovel(it.id, it.idImovelContaEnvio)
            processarDadosLigacao(it.id, it.idLigacaoAguaSituacao, it.idLigacaoEsgotoSituacao)
            adicionarDadosImovelContaEnvio(it.id)
            processarDebitoAutomatico(it.id)
        }
    }

    private fun adicionarDadosImovel(idImovel: Int?) {
        idImovel?.let {
            imovelService.obterImovelView(it)?.let { imovelView ->
                adicionarDadoAoMap("imovel", imovelView)
                adicionarEnderecoInscricao(it)
            }
        }
    }

    private fun adicionarEnderecoInscricao(idImovel: Int) {
        val endereco = enderecoService.obterEnderecoFormatadoImovel(idImovel)
        adicionarDadoAoMap("endereco", endereco)
        val inscricao = imovelService.obterDadosInscricao(idImovel)
        adicionarDadoAoMap("inscricao", inscricao)
    }

    private fun adicionarDadosCategoria(idImovel: Int?) {
        idImovel?.let {
            adicionarDadoAoMap("categorias", categoriaService.obterCategoriasView(it))
        }
    }

    private fun adicionarDadosLocalidade(idLocalidade: Int?) {
        idLocalidade?.let {
            adicionarDadoAoMap("localidade", localidadeService.obterLocalidadeView(it))

            localidadeService.obterDadosLocalidade(it).idGerenciaRegional?.let { idGerenciaRegional ->
                val gerenciaRegionalView = localidadeService.obterGerenciaRegionalView(idGerenciaRegional)
                adicionarDadoAoMap("gerenciaRegional", gerenciaRegionalView)
            }
        }
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
                            adicionarDadoAoMap("enderecoCorrespondencia", clienteService.obterEnderecoCliente(idCliente))
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

    private fun adicionarDadosImovelContaEnvio(idImovel: Int?) {
        idImovel?.let {
            imovelService.obterImovelContaEnvioView(it)?.let { imovelContaEnvioView ->
                adicionarDadoAoMap("imovelContaEnvio", imovelContaEnvioView)
            }
        }
    }

    private fun processarDebitoAutomatico(idImovel: Int?) {
        idImovel?.let {
            debitoAutomaticoService.obterDadosDebitoAutomaticoPorImovelId(it)?.idAgencia?.let { idAgencia ->
                adicionarDadoAoMap("dadosDebitoAutomatico", retornaDadosBancoAgencia(idAgencia))
            }
        }
    }

    private fun retornaDadosBancoAgencia(idAgencia: Int?) : Map<String, Any> {
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


}

