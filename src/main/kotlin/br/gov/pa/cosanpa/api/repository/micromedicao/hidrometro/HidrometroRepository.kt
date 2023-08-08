
package br.gov.pa.cosanpa.api.repository.micromedicao.hidrometro

import br.gov.pa.cosanpa.api.dominio.micromedicao.hidrometro.HidrometroInstalacaoHistorico
import br.gov.pa.cosanpa.api.dto.Dto
import br.gov.pa.cosanpa.api.dto.micromedicao.hidrometro.HidrometroDTO
import br.gov.pa.cosanpa.api.dto.micromedicao.hidrometro.HidrometroInstalacaoHistoricoDTO
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.time.LocalDate

interface HidrometroRepository : JpaRepository<HidrometroInstalacaoHistorico, Int> {

    @Query(
        value = " SELECT hidi.dataInstalacao " +
                " FROM HidrometroInstalacaoHistorico hidi " +
                " LEFT JOIN hidi.ligacaoAgua lagu " +
                " WHERE lagu.id = :idImovel " +
                " ORDER BY hidi.dataInstalacao DESC " +
                " LIMIT 1"
    )
    fun obterDataInstalacaoHIdrometroPorImovelId(idImovel: Int) : LocalDate?

    @Query(
        value = " SELECT new br.gov.pa.cosanpa.api.dto.micromedicao.hidrometro.HidrometroInstalacaoHistoricoDTO( " +
                " hidi.id, " +
                " hidi.dataInstalacao as dataInstalacao, " +
                " hidi.numeroLeituraInstalacao as numeroLeituraInstalacao, " +
                " hidi.dataRetirada as dataRetirada, " +
                " hidr.id as idHidrometro, " +
                " hidi.hidrometroProtecao.id as idHidrometroProtecao, " +
                " hli.id as idHidrometroLocalInstalacao, " +
                " rttp.id as idRateioTipo, " +
                " lagu.id as idImovel, " +
                " lagu.id as idLigacaoAgua, " +
                " hidi.medicaoTipo.id as idMedicaoTipo) " +
                " FROM HidrometroInstalacaoHistorico hidi " +
                " LEFT JOIN hidi.hidrometro hidr " +
                " LEFT JOIN hidi.ligacaoAgua lagu " +
                " LEFT JOIN hidi.hidrometroLocalInstalacao hli " +
                " LEFT JOIN hidi.rateioTipo rttp " +
                " WHERE lagu.id = :idImovel " +
                " AND hidi.dataRetirada is null " +
                " AND hidi.id = lagu.hidrometroInstalacaoHistorico.id "
    )
    fun obterDadosHidrometroInstalacaoHidrometroPorImovel(idImovel: Int): HidrometroInstalacaoHistoricoDTO?

    @Query(
        value = " SELECT new br.gov.pa.cosanpa.api.dto.micromedicao.hidrometro.HidrometroDTO( " +
                " hidr.id as id, " +
                " hidr.numero as numero, " +
                " hidr.numeroDigitosLeitura as numeroDigitosLeitura) " +
                " FROM Hidrometro hidr "+
                " WHERE hidr.id = :idHidrometro "
    )
    fun obterDadosHidrometro(idHidrometro: Int): HidrometroDTO

    @Query(
        value = " SELECT new br.gov.pa.cosanpa.api.dto.Dto( " +
                " hp.id as id, " +
                " hp.descricao as descricao) " +
                " FROM HidrometroProtecao hp " +
                " WHERE hp.id = :idHidrometroProtecao "
    )
    fun obterDadosHidrometroProtecao(idHidrometroProtecao: Int): Dto

    @Query(
        value = " SELECT new br.gov.pa.cosanpa.api.dto.Dto( " +
                " hli.id as id, " +
                " hli.descricao as descricao) " +
                " FROM HidrometroLocalInstalacao hli " +
                " WHERE hli.id = :idHidrometroLocalInstalacao "
    )
    fun obterDadosHidrometroLocalInstalacao(idHidrometroLocalInstalacao: Int): Dto

    @Query(
        value = " SELECT new br.gov.pa.cosanpa.api.dto.Dto( " +
                " rt.id as id, " +
                " rt.descricao as descricao) " +
                " FROM RateioTipo rt " +
                " WHERE rt.id = :idRateioTipo "
    )
    fun obterDadosRateioTipo(idRateioTipo: Int): Dto
}