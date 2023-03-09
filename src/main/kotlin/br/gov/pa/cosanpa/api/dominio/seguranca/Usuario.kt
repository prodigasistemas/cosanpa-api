package br.gov.pa.cosanpa.api.dominio.seguranca

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

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
)
