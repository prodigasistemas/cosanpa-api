package br.gov.pa.cosanpa.api.repository.faturamento.qualidadeagua

import br.gov.pa.cosanpa.api.dominio.faturamento.qualidadeagua.QualidadeAgua
import br.gov.pa.cosanpa.api.dto.faturamento.QualidadeAguaDTO
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface QualidadeAguaRepository: JpaRepository<QualidadeAgua, Int> {

    @Query(
        value = " SELECT new br.gov.pa.cosanpa.api.dto.faturamento.QualidadeAguaDTO( " +
                " qualidadeAgua.id as id," +
                " qualidadeAgua.anoMesReferencia as anoMesReferencia," +
                " qualidadeAgua.numeroIndiceTurbidez as numeroIndiceTurbidez," +
                " qualidadeAgua.numeroCloroResidual as numeroCloroResidual," +
                " qualidadeAgua.numeroIndicePh as numeroIndicePh," +
                " qualidadeAgua.numeroIndiceCor as numeroIndiceCor," +
                " qualidadeAgua.numeroIndiceFluor as numeroIndiceFluor," +
                " qualidadeAgua.numeroIndiceFerro as numeroIndiceFerro," +
                " qualidadeAgua.numeroIndiceColiformesTotais as numeroIndiceColiformesTotais," +
                " qualidadeAgua.numeroIndiceColiformesFecais as numeroIndiceColiformesFecais," +
                " qualidadeAgua.numeroNitrato as numeroNitrato," +
                " qualidadeAgua.numeroIndiceAlcalinidade as numeroIndiceAlcalinidade," +
                " qualidadeAgua.quantidadeTurbidezExigidas as quantidadeTurbidezExigidas," +
                " qualidadeAgua.quantidadeTurbidezAnalisadas as quantidadeTurbidezAnalisadas," +
                " qualidadeAgua.quantidadeTurbidezConforme as quantidadeTurbidezConforme," +
                " qualidadeAgua.quantidadeCorExigidas as quantidadeCorExigidas," +
                " qualidadeAgua.quantidadeCorAnalisadas as quantidadeCorAnalisadas," +
                " qualidadeAgua.quantidadeCorConforme as quantidadeCorConforme," +
                " qualidadeAgua.quantidadeCloroExigidas as quantidadeCloroExigidas," +
                " qualidadeAgua.quantidadeCloroAnalisadas as quantidadeCloroAnalisadas," +
                " qualidadeAgua.quantidadeCloroConforme as quantidadeCloroConforme," +
                " qualidadeAgua.quantidadeFluorExigidas as quantidadeFluorExigidas," +
                " qualidadeAgua.quantidadeFluorAnalisadas as quantidadeFluorAnalisadas," +
                " qualidadeAgua.quantidadeFluorConforme as quantidadeFluorConforme," +
                " qualidadeAgua.quantidadeColiformesTotaisExigidas as quantidadeColiformesTotaisExigidas," +
                " qualidadeAgua.quantidadeColiformesTotaisAnalisadas as quantidadeColiformesTotaisAnalisadas," +
                " qualidadeAgua.quantidadeColiformesTotaisConforme as quantidadeColiformesTotaisConforme," +
                " qualidadeAgua.quantidadeColiformesFecaisExigidas as quantidadeColiformesFecaisExigidas," +
                " qualidadeAgua.quantidadeColiformesFecaisAnalisadas as quantidadeColiformesFecaisAnalisadas," +
                " qualidadeAgua.quantidadeColiformesFecaisConforme as quantidadeColiformesFecaisConforme," +
                " qualidadeAgua.quantidadeColiformesTermotolerantesExigidas as quantidadeColiformesTermotolerantesExigidas," +
                " qualidadeAgua.numeroIndiceColiformesTermotolerantes as numeroIndiceColiformesTermotolerantes," +
                " qualidadeAgua.quantidadeColiformesTermotolerantesAnalisadas as quantidadeColiformesTermotolerantesAnalisadas," +
                " qualidadeAgua.quantidadeColiformesTermotolerantesConforme as quantidadeColiformesTermotolerantesConforme," +
                " qualidadeAgua.quantidadeAlcalinidadeExigidas as quantidadeAlcalinidadeExigidas," +
                " qualidadeAgua.quantidadeAlcalinidadeAnalisadas as quantidadeAlcalinidadeAnalisadas," +
                " qualidadeAgua.quantidadeAlcalinidadeConforme as quantidadeAlcalinidadeConforme," +
                " fonteCaptacao.id as idFonteCaptacao," +
                " fonteCaptacao.descricao as descricaoFonteCaptacao)" +
                " FROM QualidadeAgua qualidadeAgua " +
                " LEFT JOIN qualidadeAgua.localidade localidade " +
                " LEFT JOIN qualidadeAgua.setorComercial setorComercial " +
                " LEFT JOIN qualidadeAgua.sistemaAbastecimento sistemaAbastecimento " +
                " LEFT JOIN qualidadeAgua.fonteCaptacao fonteCaptacao " +
                " WHERE (localidade.id = :idLocalidade OR localidade.id IS NULL) " +
                " AND (setorComercial.id = :idSetorComercial OR setorComercial.id IS NULL) " +
                " AND (sistemaAbastecimento.id = :idSistemaAbastecimento OR sistemaAbastecimento.id IS NULL) " +
                " AND qualidadeAgua.anoMesReferencia = :anoMesReferencia"
    )
    fun obterQualidadeAgua(anoMesReferencia: Int,
                           idLocalidade: Int?,
                           idSetorComercial: Int?,
                           idSistemaAbastecimento: Int?): QualidadeAguaDTO?
}