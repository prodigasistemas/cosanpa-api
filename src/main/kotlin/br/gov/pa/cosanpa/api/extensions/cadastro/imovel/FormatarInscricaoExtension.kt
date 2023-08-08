package br.gov.pa.cosanpa.api.extensions.cadastro.imovel

import br.gov.pa.cosanpa.api.dto.cadastro.imovel.InscricaoDTO
import br.gov.pa.cosanpa.api.extensions.util.adicionarZerosEsquerda

fun InscricaoDTO.formatarInscricao(): String {
    var inscricao: String

    val localidade = localidadeId.toString().adicionarZerosEsquerda(3)
    val setorComercial = setorComercialCodigo.toString().adicionarZerosEsquerda(3)
    val quadra = quadraNumero.toString().adicionarZerosEsquerda(3)
    val lote = lote.toString().adicionarZerosEsquerda(4)
    val sublote = sublote.toString().adicionarZerosEsquerda(3)

    inscricao = localidade +
            setorComercial +
            quadra + lote + sublote

    return inscricao
}