package br.gov.pa.cosanpa.api.dominio.seguranca

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*
import org.springframework.security.core.GrantedAuthority

@Entity
@Table(name = "grupo", schema = "seguranca")
data class Grupo(
    @Id
    @Column(name = "grup_id")
    val id: Int? = null,
    @Column(name = "grup_dsgrupo")
    val descricao: String,

    @JsonIgnore
    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(name ="usuario_grupo", schema = "seguranca", joinColumns = [JoinColumn(name = "grup_id")], inverseJoinColumns = [JoinColumn(name = "usur_id")])
    @ElementCollection
    val usuarios: List<Usuario> = listOf()

) : GrantedAuthority {
    override fun getAuthority() = descricao
}
