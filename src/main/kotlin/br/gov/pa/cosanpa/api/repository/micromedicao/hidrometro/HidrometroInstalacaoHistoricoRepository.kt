package br.gov.pa.cosanpa.api.repository.micromedicao.hidrometro

import br.gov.pa.cosanpa.api.dominio.micromedicao.hidrometro.HidrometroInstalacaoHistorico
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
}