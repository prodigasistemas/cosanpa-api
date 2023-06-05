package br.gov.pa.cosanpa.api.service.faturamento.impressaosimultanea

import br.gov.pa.cosanpa.api.business.faturamento.impressaosimultanea.DadosConsumoTarifaBO
import br.gov.pa.cosanpa.api.business.faturamento.impressaosimultanea.DadosParaLeituraBO
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
import br.gov.pa.cosanpa.api.service.faturamento.atividade.FaturamentoAtividadeService
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
import br.gov.pa.cosanpa.api.view.DadosLeituraView
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class FaturamentoISService(
    private val imovelService: ImovelService,
    private val contaService: ContaService,
    private val enderecoService: EnderecoService,
    private val localidadeService: LocalidadeService,
    private val categoriaService: CategoriaService,
    private val clienteService: ClienteService,
    private val ligacaoService: LigacaoService,
    private val debitoAutomaticoService: DebitoAutomaticoService,
    private val bancoService: BancoService,
    private val consumoHistoricoService: ConsumoHistoricoService,
    private val medicaoHistoricoService: MedicaoHistoricoService,
    private val debitoService: DebitoService,
    private val consumoTarifaService: ConsumoTarifaService,
    private val sistemaParametrosService: SistemaParametrosService,
    private val extratoQuitacaoService: ExtratoQuitacaoService,
    private val qualidadeAguaService: QualidadeAguaService,
    private val faturamentoSituacaoService: FaturamentoSituacaoService,
    private val leituraAnormalidadeService: LeituraAnormalidadeService,
    private val creditoService: CreditoService,
    private val cobrancaDocumentoService: CobrancaDocumentoService,
    private val hidrometroService: HidrometroService,
    private val faturamentoAtividadeService: FaturamentoAtividadeService,
) {

    fun obterDadosParaLeituraImovelUnico(idImovel: Int): ResponseEntity<DadosLeituraView> {
        val dadosView = getInstanciaDadosLeitura().obterPorImovel(idImovel)

        return if (dadosView.dadosImovel.isEmpty()) {
            ResponseEntity.noContent().build()
        } else {
            ResponseEntity.ok().body(dadosView)
        }
    }

    fun obterDadosParaLeituraPorRota(idRota: Int): ResponseEntity<List<DadosLeituraView>> {
        val idsImoveis = imovelService.obterIdsImovelPorRota(idRota)

        return if (gerarDadosColecaoImoveis(idsImoveis).isEmpty()) {
            ResponseEntity.noContent().build()
        } else {
            ResponseEntity.ok().body(gerarDadosColecaoImoveis(idsImoveis))
        }
    }

    fun gerarDadosColecaoImoveis(idsImoveis: List<Int>): List<DadosLeituraView> {
        val gerarDadosLista = mutableListOf<DadosLeituraView>()
        var gerarDadosView: DadosLeituraView

        idsImoveis.forEach { idImovel ->
            gerarDadosView = getInstanciaDadosLeitura().obterPorImovel(idImovel)

            if (gerarDadosView.dadosImovel.isNotEmpty()) gerarDadosLista.add(gerarDadosView)
        }

        return gerarDadosLista
    }

    fun obterDadosConsumoTarifaPorGrupo(idGrupo: Int): ResponseEntity<MutableMap<String, Any>>  {
        val dadosConsumoTarifa = DadosConsumoTarifaBO(
            sistemaParametrosService,
            consumoTarifaService,
            faturamentoAtividadeService,
            categoriaService,
            imovelService
        ).obter(idGrupo)

        return if (dadosConsumoTarifa.isEmpty()) {
            ResponseEntity.noContent().build()
        } else {
            ResponseEntity.ok().body(dadosConsumoTarifa)
        }
    }


    private fun getInstanciaDadosLeitura() = DadosParaLeituraBO(
        imovelService = imovelService,
        contaService = contaService,
        localidadeService = localidadeService,
        categoriaService = categoriaService,
        enderecoService = enderecoService,
        clienteService = clienteService,
        ligacaoService = ligacaoService,
        faturamentoSituacaoService = faturamentoSituacaoService,
        leituraAnormalidadeService = leituraAnormalidadeService,
        debitoAutomaticoService = debitoAutomaticoService,
        bancoService = bancoService,
        consumoHistoricoService = consumoHistoricoService,
        medicaoHistoricoService = medicaoHistoricoService,
        hidrometroService = hidrometroService,
        debitoService = debitoService,
        creditoService = creditoService,
        consumoTarifaService = consumoTarifaService,
        extratoQuitacaoService = extratoQuitacaoService,
        qualidadeAguaService = qualidadeAguaService,
        cobrancaDocumentoService = cobrancaDocumentoService,
        sistemaParametrosService = sistemaParametrosService
        )
}