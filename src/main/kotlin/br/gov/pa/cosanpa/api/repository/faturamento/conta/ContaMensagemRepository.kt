package br.gov.pa.cosanpa.api.repository.faturamento.conta

import br.gov.pa.cosanpa.api.dominio.faturamento.conta.ContaMensagem
import br.gov.pa.cosanpa.api.dto.faturamento.conta.ContaMensagemDTO
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface ContaMensagemRepository : JpaRepository<ContaMensagem, Int> {
    
    @Query(
            value = "SELECT new br.gov.pa.cosanpa.api.dto.faturamento.conta.ContaMensagemDTO( " +
                    " contaMensagem.descricaoContaMensagem01 as descricaoContaMensagem01, " +
                    " contaMensagem.descricaoContaMensagem02 as descricaoContaMensagem02, " +
                    " contaMensagem.descricaoContaMensagem03 as descricaoContaMensagem03) " +
                    " FROM ContaMensagem contaMensagem  " +
                    " LEFT JOIN contaMensagem.gerenciaRegional gerenciaRegional  " +
                    " LEFT JOIN contaMensagem.localidade localidade  " +
                    " LEFT JOIN contaMensagem.setorComercial setorComercial  " +
                    " LEFT JOIN contaMensagem.faturamentoGrupo faturamentoGrupo  " +
                    " LEFT JOIN contaMensagem.quadra quadra  " +
                    " WHERE contaMensagem.anoMesReferenciaFaturamento = :amReferencia  " +
                    " AND (faturamentoGrupo.id = :idFaturamentoGrupo OR faturamentoGrupo.id IS NULL) " +
                    " AND (gerenciaRegional.id = :idGerenciaRegional OR gerenciaRegional.id IS NULL)" +
                    " AND (localidade.id = :idLocalidade OR localidade.id IS NULL) " +
                    " AND (setorComercial.id = :idSetorComercial OR setorComercial.id IS NULL)" +
                    " AND (quadra.id = :idQuadra OR quadra.id IS NULL)"
    )
    fun obterContaMensagem3Partes(amReferencia: Int,
                                  idFaturamentoGrupo: Int?,
                                  idGerenciaRegional: Int?,
                                  idLocalidade: Int?,
                                  idSetorComercial: Int?,
                                  idQuadra: Int?) : ContaMensagemDTO
}