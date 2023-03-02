package br.gov.pa.cosanpa.api.extensions.imovel

import br.gov.pa.cosanpa.api.dominio.cadastro.Imovel
import br.gov.pa.cosanpa.api.extensions.util.adicionarZerosEsqueda

fun Imovel.formatarInscricao(): String {
    var inscricao = ""

    val localidade = localidade.id.toString().adicionarZerosEsqueda(3)
    val setorComercial = setorComercial.codigo.toString().adicionarZerosEsqueda(3)
    val quadra = quadra.numero.toString().adicionarZerosEsqueda(3)
    val lote = lote.toString().adicionarZerosEsqueda(3)

    inscricao = localidade+
                setorComercial+
                quadra + lote + sublote

    return inscricao
}