package br.gov.pa.cosanpa.api.repository.micromedicao.consumohistorico

import br.gov.pa.cosanpa.api.dominio.micromedicao.consumo.ConsumoHistorico
import br.gov.pa.cosanpa.api.dto.micromedicao.consumo.ConsumoHistoricoDTO
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface ConsumoHistoricoRepository : JpaRepository<ConsumoHistorico, Int> {

    @Query(
        value = "SELECT  new br.gov.pa.cosanpa.api.dto.micromedicao.consumo.ConsumoHistoricoDTO(ch.referencia as referencia, ch.numeroCalculoConsumoMedia as numeroCalculoConsumoMedia) " +
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
    fun obterVolumeMedioAguaEsgoto(
        idImovel: Int,
        idLigacao: Int,
        amReferenciaInicial: Int,
        amReferenciaFinal: Int
    ): List<ConsumoHistoricoDTO>
}