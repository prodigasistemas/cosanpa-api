package br.gov.pa.cosanpa.api.repository.micromedicao.hidrometro

import br.gov.pa.cosanpa.api.dominio.micromedicao.hidrometro.HidrometroInstalacaoHistorico
import br.gov.pa.cosanpa.api.dto.micromedicao.hidrometro.HidrometroDTO
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.time.LocalDate

interface HidrometroInstalacaoHistoricoRepository : JpaRepository<HidrometroInstalacaoHistorico, Int> {

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
        value = " SELECT new br.gov.pa.cosanpa.api.dto.micromedicao.hidrometro.HidrometroDTO( " +
                " hidr.numero as numero, " +
                " hidr.numeroDigitosLeitura as numeroDigitosLeitura, " +
                " hidi.dataInstalacao as dataInstalacao, " +
                " hidi.numeroLeituraInstalacao as numeroLeituraInstalacao, " +
                " lagu.id as idImovel, " +
                " hli.descricao as descricaoHidrometroLocalInstalacao, " +
                " rttp.id as idRateioTipo)" +
                " FROM HidrometroInstalacaoHistorico hidi " +
                " LEFT JOIN hidi.hidrometro hidr " +
                " LEFT JOIN hidi.ligacaoAgua lagu " +
                " LEFT JOIN hidi.hidrometroLocalInstalacao hli " +
                " LEFT JOIN hidi.rateioTipo rttp " +
                " WHERE lagu.id = :idImovel " +
                " AND hidi.dataRetirada is null " +
                " AND hidi.id = lagu.hidrometroInstalacaoHistorico.id "
    )
    fun obterDadosHidrometro(idImovel: Int): HidrometroDTO

}