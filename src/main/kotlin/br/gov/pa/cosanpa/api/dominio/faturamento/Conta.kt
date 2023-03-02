package br.gov.pa.cosanpa.api.dominio.faturamento

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.util.Date

@Entity
@Table(name = "conta", schema = "faturamento")
data class Conta(
    @Id
    @Column(name = "cnta_id")
    val id: Int? = null,

    @Column(name = "cnta_dtvencimento")
    val dataVencimento: Date,
    @Column(name = "cnta_dtvalidade")
    val dataValidade: Date,
    @Column(name = "cnta_dgverificadorconta")
    val digitoVerificador: Int,
    @Column(name = "cnta_amreferenciaconta")
    val referencia: Int,
)
