package br.gov.pa.cosanpa.api.domain.usuario.usuariogrupo

import br.gov.pa.cosanpa.api.domain.usuario.Grupo
import br.gov.pa.cosanpa.api.domain.usuario.Usuario
import jakarta.persistence.*
import java.io.Serializable

@Entity
@Table(name = "usuario_grupo", schema = "seguranca")
@IdClass(UsuarioGrupoPK::class)
data class UsuarioGrupo (
    @Id
    @Column(name = "usur_id")
    val usuarioId: Int? = null,
    @Id
    @Column(name = "grup_id")
    val grupoId: Int? = null,

    @ManyToOne
    val usuario: Usuario,
    @ManyToOne
    val grupo: Grupo
) : Serializable