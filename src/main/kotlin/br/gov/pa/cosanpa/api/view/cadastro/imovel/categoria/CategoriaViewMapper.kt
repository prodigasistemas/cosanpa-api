package br.gov.pa.cosanpa.api.view.cadastro.imovel.categoria

import br.gov.pa.cosanpa.api.business.cadastro.imovel.DadosConsumosCategoriaBO
import br.gov.pa.cosanpa.api.dto.cadastro.imovel.CategoriaDTO
import br.gov.pa.cosanpa.api.view.DtoViewMapper
import org.springframework.stereotype.Component
import java.math.BigDecimal

@Component
class CategoriaViewMapper : DtoViewMapper {
    fun mapCategoria(entity: CategoriaDTO): CategoriaView {
        return CategoriaView(
            idCategoria = entity.id ?: 0,
            descricaoCategoria = entity.descricao ?: "",
            quantidadeEconomias = entity.quantidadeEconomias?.toInt() ?: 0,
            idSubcategoria = entity.idSubcategoria ?: 0,
            descricaoSubcategoria = entity.descricaoSubcategoria ?: "",
            consumoReferenciaBaixoConsumo = DadosConsumosCategoriaBO(entity).obterReferenciaBaixoConsumo() ?: 0,
            consumoReferenciaAltoConsumo = DadosConsumosCategoriaBO(entity).obterReferenciaAltoConsumo() ?: 0,
            consumoReferenciaEstouroConsumo = DadosConsumosCategoriaBO(entity).obterReferenciaEstouroConsumo() ?: 0,
            consumoMaximoEstouroConsumo = DadosConsumosCategoriaBO(entity).obterMaximoEstouroConsumo() ?: 0,
            percentualDeterminacaoBaixoConsumo = entity.porcentagemMediaBaixoConsumo ?: BigDecimal.ZERO,
            vezesMediaAltoConsumo = entity.vezesMediaAltoConsumo ?: BigDecimal.ZERO,
            vezesMediaEstouroConsumo = entity.vezesMediaEstouro ?: BigDecimal.ZERO
        )
    }

}