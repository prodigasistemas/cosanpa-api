package br.gov.pa.cosanpa.api.domain.usuario.usuariogrupo

import java.io.Serializable


data class UsuarioGrupoPK(
    val usuarioId: Int? = null,
    val grupoId: Int? = null
) : Serializable
