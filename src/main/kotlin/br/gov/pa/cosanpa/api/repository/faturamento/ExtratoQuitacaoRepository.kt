package br.gov.pa.cosanpa.api.repository.faturamento

import br.gov.pa.cosanpa.api.dominio.faturamento.ExtratoQuitacao
import br.gov.pa.cosanpa.api.dto.faturamento.ExtratoQuitacaoDTO
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface ExtratoQuitacaoRepository: JpaRepository<ExtratoQuitacao, Int> {
    
    @Query(
        value = " SELECT new br.gov.pa.cosanpa.api.dto.faturamento.ExtratoQuitacaoDTO(" +
                " extrato.id as id," +
                " extrato.anoReferencia as anoReferencia," +
                " extrato.indicadorImpressaoNaConta as indicadorImpressaoNaConta) " +
                " FROM ExtratoQuitacao extrato " +
                " INNER JOIN extrato.imovel imovel " +
                " WHERE extrato.anoReferencia = :anoReferencia " +
                " AND imovel.id = :idImovel"
    )
    fun obterExtratoQuitacaoImovel(idImovel: Int, anoReferencia: Int): ExtratoQuitacaoDTO?
}