package br.gov.pa.cosanpa.api.dominio.seguranca

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*

@Entity
@Table(name = "usuario", schema = "seguranca")
data class Usuario(
    @Id
    @Column(name = "usur_id")
    val id: Int? = null,
    @Column(name = "usur_nmlogin")
    val login: String = "",
    @Column(name = "usur_nmsenha")
    val senha: String = "",
    @Column(name = "usur_nmusuario")
    val nome: String = "",

    @JsonIgnore
    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(name ="usuario_grupo", schema = "seguranca", joinColumns = [JoinColumn(name = "usur_id")], inverseJoinColumns = [JoinColumn(name = "grup_id")])
    val grupos: List<Grupo> = listOf()
)
