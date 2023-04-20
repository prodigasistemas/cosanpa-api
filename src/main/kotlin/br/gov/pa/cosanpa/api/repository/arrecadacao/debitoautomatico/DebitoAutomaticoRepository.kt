package br.gov.pa.cosanpa.api.repository.arrecadacao.debitoautomatico

import br.gov.pa.cosanpa.api.dominio.arrecadacao.DebitoAutomatico
import br.gov.pa.cosanpa.api.dto.arrecadacao.debitoautomatico.DebitoAutomaticoDTO
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface DebitoAutomaticoRepository : JpaRepository<DebitoAutomatico, Int> {

    @Query(
        value = " SELECT new br.gov.pa.cosanpa.api.dto.arrecadacao.debitoautomatico.DebitoAutomaticoDTO( " +
                " deba.id as id, " +
                " deba.identificacaoClienteBanco as identificacaoClienteBanco, " +
                " deba.dataExclusao as dataExclusao, " +
                " imovel.id as idImovel, " +
                " ag.id as idAgencia) " +
                " FROM DebitoAutomatico deba " +
                " INNER JOIN deba.agencia ag " +
                " INNER JOIN deba.imovel imovel " +
                " WHERE imovel.id = :idImovel"
    )
    fun obterDadosDebitoAutomaticoPorImovelId(idImovel: Int) : DebitoAutomaticoDTO?
}