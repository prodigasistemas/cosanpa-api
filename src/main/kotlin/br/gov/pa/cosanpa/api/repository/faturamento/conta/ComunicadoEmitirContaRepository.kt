package br.gov.pa.cosanpa.api.repository.faturamento.conta

import br.gov.pa.cosanpa.api.dominio.faturamento.conta.ComunicadoEmitirConta
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface ComunicadoEmitirContaRepository: JpaRepository<ComunicadoEmitirConta, Int> {
    
    @Query(
        value = " SELECT COUNT(comunicado.id) FROM ComunicadoEmitirConta comunicado " +
                " INNER JOIN comunicado.imovel imovel " +
                " WHERE imovel.id = :idImovel " +
                " and comunicado.tipoComunicado = :tipoComunicado " +
                " and comunicado.indicadorEmitido = :indicadorEmitido " +
                " and comunicado.referencia = :referencia "
    )
    fun possuiComunicadoEmitido(idImovel: Int, indicadorEmitido: Int, tipoComunicado: Int, referencia: Int): Number
}