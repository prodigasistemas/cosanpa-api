package br.gov.pa.cosanpa.api.repository.micromedicao.leitura.anormalidade

import br.gov.pa.cosanpa.api.dominio.micromedicao.leitura.anormalidade.LeituraAnormalidadeLeitura
import br.gov.pa.cosanpa.api.dto.Dto
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface LeituraAnormalidadeRepository : JpaRepository<LeituraAnormalidadeLeitura, Int> {

    @Query(
        value = " SELECT new br.gov.pa.cosanpa.api.dto.Dto( " +
                " la.id as id, " +
                " la.descricao as descricao) " +
                " FROM LeituraAnormalidade la " +
                " WHERE la.id = :idLeituraAnormalidade "
    )
    fun obterDadosLeituraAnormalidade(idLeituraAnormalidade: Int) : Dto

    @Query(
        value = " SELECT new br.gov.pa.cosanpa.api.dto.Dto( " +
                " lal.id as id, " +
                " lal.descricaoFaturamento as descricao) " +
                " FROM LeituraAnormalidadeLeitura lal " +
                " WHERE lal.id = :idLeituraAnormalidadeLeitura "
    )
    fun obterDadosLeituraAnormalidadeLeitura(idLeituraAnormalidadeLeitura: Int) : Dto

    @Query(
        value = " SELECT new br.gov.pa.cosanpa.api.dto.Dto( " +
                " lac.id as id, " +
                " lac.descricaoConsumoACobrar as descricao) " +
                " FROM LeituraAnormalidadeConsumo lac " +
                " WHERE lac.id = :idLeituraAnormalidadeConsumo "
    )
    fun obterDadosLeituraAnormalidadeConsumo(idLeituraAnormalidadeConsumo: Int) : Dto


}