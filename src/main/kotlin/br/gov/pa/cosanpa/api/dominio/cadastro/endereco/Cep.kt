package br.gov.pa.cosanpa.api.dominio.cadastro.endereco

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "cep", schema = "cadastro")
data class Cep(
    @Id
    @Column(name = "cep_id")
    val id: Int? = null,
    @Column(name = "cep_nmlogradouro")
    val logradouro: String = "",
    @Column(name = "cep_dslogradourotipo")
    val descricaoTipoLogradouro: String,
    @Column(name = "cep_nmbairro")
    val bairro: String = "",
    @Column(name = "cep_nmmunicipio")
    val municipio: String = "",
    @Column(name = "cep_dsufsigla")
    val sigla: String = "",
    @Column(name = "cep_cdcep")
    val codigo: Int

)