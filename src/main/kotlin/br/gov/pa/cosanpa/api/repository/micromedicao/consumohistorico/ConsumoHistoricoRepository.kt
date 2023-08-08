package br.gov.pa.cosanpa.api.repository.micromedicao.consumohistorico

import br.gov.pa.cosanpa.api.dominio.micromedicao.consumo.ConsumoHistorico
import br.gov.pa.cosanpa.api.dto.Dto
import br.gov.pa.cosanpa.api.dto.micromedicao.consumo.ConsumoHistoricoDTO
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface ConsumoHistoricoRepository : JpaRepository<ConsumoHistorico, Int> {

    @Query(
        value = "SELECT  new br.gov.pa.cosanpa.api.dto.micromedicao.consumo.ConsumoHistoricoDTO( " +
                " ch.id as id, " +
                " ch.referencia as referencia, " +
                " ch.numeroCalculoConsumoMedia as numeroCalculoConsumoMedia," +
                " ch.numeroConsumoFaturadoMes as numeroConsumoFaturadoMes," +
                " ch.imovel.id as idImovel," +
                " ch.consumoTipo.id as idConsumoTipo," +
                " ch.consumoAnormalidade.id as idConsumoAnormalidade," +
                " ch.ligacaoTipo.id as idLigacaoTipo) " +
                " FROM ConsumoHistorico ch " +
                " INNER JOIN ch.consumoTipo ct " +
                " LEFT JOIN ch.consumoAnormalidade ca " +
                " with ca.indicadorCalcularMedia = 1 " +
                " WHERE ch.imovel.id = :idImovel " +
                " and ch.ligacaoTipo.id = :idLigacao " +
                " and ch.referencia between :amReferenciaInicial and :amReferenciaFinal " +
                " and ct.indicadorCalcularMedia = 1 " +
                " ORDER BY ch.referencia desc"
    )
    fun obterConsumosEntreReferencias(
        idImovel: Int,
        idLigacao: Int,
        amReferenciaInicial: Int,
        amReferenciaFinal: Int
    ): List<ConsumoHistoricoDTO>

    @Query(
        value = "SELECT  new br.gov.pa.cosanpa.api.dto.micromedicao.consumo.ConsumoHistoricoDTO(" +
                " ch.id as id, " +
                " ch.referencia as referencia, " +
                " ch.numeroCalculoConsumoMedia as numeroCalculoConsumoMedia," +
                " ch.numeroConsumoFaturadoMes as numeroConsumoFaturadoMes," +
                " ch.imovel.id as idImovel," +
                " ch.consumoTipo.id as idConsumoTipo," +
                " ch.consumoAnormalidade.id as idConsumoAnormalidade," +
                " ch.ligacaoTipo.id as idLigacaoTipo) " +
                " FROM ConsumoHistorico ch " +
                " INNER JOIN ch.consumoTipo ct " +
                " LEFT JOIN ch.consumoAnormalidade ca " +
                " with ca.indicadorCalcularMedia = 1 " +
                " WHERE ch.imovel.id = :idImovel " +
                " and ch.ligacaoTipo.id = :idLigacaoTipo " +
                " ORDER BY ch.referencia DESC " +
                " LIMIT 6 "
    )
    fun obterColecaoUltimosConsumos(idImovel: Int, idLigacaoTipo: Int): List<ConsumoHistoricoDTO>

    @Query(
        value = " SELECT new br.gov.pa.cosanpa.api.dto.Dto( " +
                " csan.id as id, " +
                " csan.descricao as descricao) " +
                " FROM ConsumoAnormalidade csan " +
                " WHERE csan.id = :idConsumoAnormalidade "
    )
    fun obterDadosConsumoAnormalidade(idConsumoAnormalidade: Int) : Dto
}