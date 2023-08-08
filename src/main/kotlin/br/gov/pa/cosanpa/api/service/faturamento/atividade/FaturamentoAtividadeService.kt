package br.gov.pa.cosanpa.api.service.faturamento.atividade

import br.gov.pa.cosanpa.api.repository.faturamento.atividade.FaturamentoAtividadeRepository
import org.springframework.stereotype.Service

@Service
class FaturamentoAtividadeService(
    private val repository: FaturamentoAtividadeRepository
) {

    fun obterDataPrevista(
        idFaturamentoGrupo: Int,
        idFaturamentoAtividade: Int,
        referenciaFaturamento: Int
    ) = repository.obterDataPrevista(idFaturamentoGrupo, idFaturamentoAtividade, referenciaFaturamento)

    fun obterDataRealizacao(
        idFaturamentoGrupo: Int,
        idFaturamentoAtividade: Int,
        referenciaFaturamento: Int
    ) = repository.obterDataRealizacao(idFaturamentoGrupo, idFaturamentoAtividade, referenciaFaturamento)
}