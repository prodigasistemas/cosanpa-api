package br.gov.pa.cosanpa.api.repository.arrecadacao.banco

import br.gov.pa.cosanpa.api.dominio.arrecadacao.Banco
import br.gov.pa.cosanpa.api.dto.DTO
import br.gov.pa.cosanpa.api.dto.arrecadacao.banco.AgenciaDTO
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface BancoRepository : JpaRepository<Banco, Int> {

    @Query(
        value = " SELECT new br.gov.pa.cosanpa.api.dto.DTO(" +
                " b.id as id, " +
                " b.nome as nome) " +
                " FROM Banco b " +
                " WHERE b.id = :idBanco"
    )
    fun obterDadosBanco(idBanco: Int) : DTO

    @Query(
        value = " SELECT new br.gov.pa.cosanpa.api.dto.arrecadacao.banco.AgenciaDTO( " +
                " ag.id as id, " +
                " ag.codigo as codigo, " +
                " ag.banco.id as idBanco) " +
                " FROM Agencia ag " +
                " WHERE ag.id = :idAgencia"
    )
    fun obterDadosAgencia(idAgencia: Int) : AgenciaDTO
}