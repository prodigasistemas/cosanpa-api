package br.gov.pa.cosanpa.api.repository.faturamento.conta

import br.gov.pa.cosanpa.api.dominio.faturamento.conta.ContaImpostosDeduzidos
import br.gov.pa.cosanpa.api.dto.faturamento.conta.ContaImpostosDeduzidosDTO
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface ContaImpostosDeduzidosRepository : JpaRepository<ContaImpostosDeduzidos, Int> {

    @Query(
        value = " SELECT new br.gov.pa.cosanpa.api.dto.faturamento.conta.ContaImpostosDeduzidosDTO( " +
                " contaImpostosDeduzidos.percentualAliquota as percentualAliquota," +
                " contaImpostosDeduzidos.valorImposto as valorImposto, " +
                " impostoTipo.id as idImpostoTipo, " +
                " impostoTipo.descricao as descricaoImpostoTipo) " +
                " FROM ContaImpostosDeduzidos contaImpostosDeduzidos " +
                " INNER JOIN contaImpostosDeduzidos.conta conta " +
                " INNER JOIN contaImpostosDeduzidos.impostoTipo impostoTipo " +
                " WHERE conta.id = :idConta "
    )
    fun obterDadosContaImpostosDeduzidosDTO(idConta: Int) : List<ContaImpostosDeduzidosDTO>
}