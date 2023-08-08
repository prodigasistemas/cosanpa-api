package br.gov.pa.cosanpa.api.view.faturamento.conta

import br.gov.pa.cosanpa.api.dto.faturamento.conta.ContaDTO
import br.gov.pa.cosanpa.api.dto.faturamento.conta.ContaImpostosDeduzidosDTO
import br.gov.pa.cosanpa.api.dto.faturamento.conta.ContaMensagemDTO
import br.gov.pa.cosanpa.api.extensions.util.conveterLocalDateParaddMMyyyyComBarras
import br.gov.pa.cosanpa.api.view.DtoViewMapper
import org.springframework.stereotype.Component
import java.math.BigDecimal
import java.math.RoundingMode

@Component
class ContaViewMapper : DtoViewMapper {

    fun mapConta(entity: ContaDTO): ContaView {
        return ContaView(
            id = entity.id ?: 0,
            referencia = entity.referencia ?: 0,
            dataVencimento = entity.dataVencimento?.conveterLocalDateParaddMMyyyyComBarras() ?: "",
            dataValidade = entity.dataValidade?.conveterLocalDateParaddMMyyyyComBarras() ?: "",
            digitoVerificador = entity.digitoVerificador ?: 0,
            lote = entity.lote ?: 0,
            sublote = entity.sublote ?: 0,
            percentualEsgoto = entity.percentualEsgoto?.setScale(2, RoundingMode.HALF_UP) ?: BigDecimal.ZERO,
            codigoSetorComercial = entity.codigoSetorComercial ?: 0,
            quadra = entity.quadra ?: 0,
            idDebitoCreditoSituacaoAnterior = entity.idDebitoCreditoSituacaoAnterior ?: 0,
            idDebitoCreditoSituacaoAtual = entity.idDebitoCreditoSituacaoAtual ?: 0
        )
    }

    fun mapMensagemConta(entity: ContaMensagemDTO): String {
        return "${entity.descricaoContaMensagem01 ?: ""} ${entity.descricaoContaMensagem02 ?: ""} ${entity.descricaoContaMensagem03 ?: ""}"
    }

    fun mapContaImpostosDeduzidos(entity: ContaImpostosDeduzidosDTO): ContaImpostosDeduzidosView {
        return ContaImpostosDeduzidosView(
            id = entity.id ?: 0,
            valorBaseCalculo = entity.valorBaseCalculo ?: BigDecimal.ZERO,
            valorImposto = entity.valorImposto ?: BigDecimal.ZERO,
            percentualAliquota = entity.percentualAliquota ?: BigDecimal.ZERO
        )
    }
}
