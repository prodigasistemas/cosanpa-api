package br.gov.pa.cosanpa.api.repository.consumo

import br.gov.pa.cosanpa.api.dominio.micromedicao.consumo.ConsumoHistorico
import br.gov.pa.cosanpa.api.view.consumo.ConsumoHistoricoView
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface ConsumoHistoricoRepository : JpaRepository<ConsumoHistorico, Int> {

    @Query(
        value = "select new br.gov.pa.cosanpa.api.view.consumo.ConsumoHistoricoView(ch.referencia as referencia, ch.numeroCalculoConsumoMedia as numeroCalculoConsumoMedia) " +
                " from ConsumoHistorico ch " +
                " inner join ch.consumoTipo ct " +
                " left join ch.consumoAnormalidade ca " +
                " with ca.indicadorCalcularMedia = 1 " +
                " where ch.imovel.id = :idImovel " +
                " and ch.ligacaoTipo.id = :idLigacao " +
                " and ch.referencia between :amReferenciaInicial and :amReferenciaFinal " +
                " and ct.indicadorCalcularMedia = 1 " +
                " order by ch.referencia desc"
    )
    fun obterVolumeMedioAguaEsgoto(
        idImovel: Int,
        idLigacao: Int,
        amReferenciaInicial: Int,
        amReferenciaFinal: Int
    ): List<ConsumoHistoricoView>
}