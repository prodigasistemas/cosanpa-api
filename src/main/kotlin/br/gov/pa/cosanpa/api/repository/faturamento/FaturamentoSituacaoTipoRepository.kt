package br.gov.pa.cosanpa.api.repository.faturamento

import br.gov.pa.cosanpa.api.dominio.faturamento.FaturamentoSituacaoTipo
import br.gov.pa.cosanpa.api.dto.faturamento.FaturamentoSituacaoTipoDTO
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface FaturamentoSituacaoTipoRepository: JpaRepository<FaturamentoSituacaoTipo, Int> {
    
    @Query(
        value = " SELECT new br.gov.pa.cosanpa.api.dto.faturamento.FaturamentoSituacaoTipoDTO(" +
                " fst.id as id, " +
                " fst.leituraAnormalidadeConsumoSemLeitura.id as idLeituraAnormalidadeConsumoSemLeitura, " +
                " fst.leituraAnormalidadeConsumoComLeitura.id as idLeituraAnormalidadeConsumoComLeitura, " +
                " fst.leituraAnormalidadeLeituraSemLeitura.id as idLeituraAnormalidadeLeituraSemLeitura, " +
                " fst.leituraAnormalidadeLeituraComLeitura.id as idLeituraAnormalidadeLeituraComLeitura) " +
                " FROM FaturamentoSituacaoTipo fst " +
                " LEFT JOIN fst.leituraAnormalidadeConsumoSemLeitura " +
                " LEFT JOIN fst.leituraAnormalidadeConsumoComLeitura " +
                " LEFT JOIN fst.leituraAnormalidadeLeituraSemLeitura " +
                " LEFT JOIN fst.leituraAnormalidadeLeituraComLeitura " +
                " WHERE fst.id = :idFaturamentoSituacaoTipo"
    )
    fun obterFaturamentoSituacaoTipo(idFaturamentoSituacaoTipo: Int): FaturamentoSituacaoTipoDTO?
}