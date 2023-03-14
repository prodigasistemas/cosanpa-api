package br.gov.pa.cosanpa.api.dominio.cadastro.cliente

import br.gov.pa.cosanpa.api.dominio.cadastro.endereco.EnderecoReferencia
import br.gov.pa.cosanpa.api.dominio.cadastro.endereco.Logradouro
import br.gov.pa.cosanpa.api.dominio.cadastro.endereco.LogradouroBairro
import br.gov.pa.cosanpa.api.dominio.cadastro.endereco.LogradouroCep
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table

@Entity
@Table(name = "cliente_endereco", schema = "cadastro")
data class ClienteEndereco(
    @Id
    @Column(name = "cled_id")
    val id: Int,
    @Column(name = "cled_nnimovel")
    val numero: String?,
    @Column(name = "cled_dscomplementoendereco")
    val complementoEndereco: String?,
    @Column(name = "cled_icenderecocorrespondencia")
    val indicadorEnderecoCorrespondencia: Short?,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "clie_id")
    val cliente: Cliente,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lgcp_id")
    val logradouroCep: LogradouroCep,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lgbr_id")
    val logradouroBairro: LogradouroBairro,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "edrf_id")
    val enderecoReferencia: EnderecoReferencia,

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(
        name = "logr_idinicioperimetro",
        referencedColumnName = "logr_id",
        insertable = false,
        updatable = false
    )
    val perimetroInicial: Logradouro,

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "logr_idfimperimetro", referencedColumnName = "logr_id", insertable = false, updatable = false)
    val perimetroFinal: Logradouro
) {
    companion object {
        const val INDICADOR_ENDERECO_CORRESPONDENCIA: Short = 1
        const val INDICADOR_NAO_ENDERECO_CORRESPONDENCIA: Short = 2
    }
}

