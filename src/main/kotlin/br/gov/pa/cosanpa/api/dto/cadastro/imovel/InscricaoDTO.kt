package br.gov.pa.cosanpa.api.dto.cadastro.imovel

data class InscricaoDTO(
        val localidadeId: Int,
        val setorComercialCodigo: Int,
        val quadraNumero: Int,
        val lote: Int,
        val sublote: Int,
)
