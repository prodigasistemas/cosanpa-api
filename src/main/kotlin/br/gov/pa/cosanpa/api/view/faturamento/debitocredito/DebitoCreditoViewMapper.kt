package br.gov.pa.cosanpa.api.view.faturamento.debitocredito

import br.gov.pa.cosanpa.api.dto.faturamento.debitocredito.CreditoRealizadoDTO
import br.gov.pa.cosanpa.api.dto.faturamento.debitocredito.DebitoCobradoDTO
import br.gov.pa.cosanpa.api.dto.faturamento.debitocredito.DebitoCreditoTipoDTO
import br.gov.pa.cosanpa.api.view.DtoViewMapper
import org.springframework.stereotype.Component
import java.math.BigDecimal
import java.math.RoundingMode

@Component
class DebitoCreditoViewMapper : DtoViewMapper {

    fun mapDebitoCobrado(entity: DebitoCobradoDTO): DebitoCobradoView {
        return DebitoCobradoView(
            id = entity.id ?: 0,
            referencia = entity.referencia ?: 0,
            numeroPrestacao = BigDecimal(entity.numeroPrestacao?.toInt() ?: 0),
            numeroPrestacaoDebito = entity.numeroPrestacaoDebito ?: 0,
            numeroParcelaBonus = entity.numeroParcelaBonus ?: 0,
            valorServico = BigDecimal(entity.valorServico?.toDouble() ?: 0.0).setScale(2, RoundingMode.HALF_UP)
        )
    }

    fun mapCreditoRealizado(entity: CreditoRealizadoDTO): CreditoRealizadoView {
        return CreditoRealizadoView(
            id = entity.id ?: 0,
            referencia = entity.referencia ?: 0,
            numeroPrestacao = BigDecimal(entity.numeroPrestacao?.toInt() ?: 0),
            numeroPrestacaoCredito = entity.numeroPrestacaoCredito ?: 0,
            numeroParcelaBonus = entity.numeroParcelaBonus ?: 0,
            valorCredito = BigDecimal(entity.valorCredito?.toDouble() ?: 0.0).setScale(2, RoundingMode.HALF_UP)
        )
    }

    fun mapDebitoCreditoTipo(entity: DebitoCreditoTipoDTO): DebitoCreditoTipoView {
        return DebitoCreditoTipoView(
            id = entity.id ?: 0,
            descricao = entity.descricao ?: "",
            codigoConstante = entity.codigoConstante ?: 0
        )
    }
}
