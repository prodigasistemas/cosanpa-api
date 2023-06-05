package br.gov.pa.cosanpa.api.view.faturamento.conta

import br.gov.pa.cosanpa.api.dto.faturamento.conta.ContaImpostosDeduzidosDTO
import br.gov.pa.cosanpa.api.dto.faturamento.conta.ContaMensagemDTO
import br.gov.pa.cosanpa.api.view.DtoViewMapper
import org.springframework.stereotype.Component
import java.math.BigDecimal

@Component
class ContaViewMapper : DtoViewMapper {
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
