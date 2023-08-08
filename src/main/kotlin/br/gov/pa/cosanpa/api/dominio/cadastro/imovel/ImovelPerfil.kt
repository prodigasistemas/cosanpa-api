package br.gov.pa.cosanpa.api.dominio.cadastro.imovel

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "imovel_perfil", schema = "cadastro")
data class ImovelPerfil(
    @Id
    @Column(name = "iper_id")
    val id: Int,
    @Column(name = "iper_dsimovelperfil")
    val descricao: String?,
    @Column(name = "iper_icgerardadosleitura")
    val indicadorGerarDadosLeitura: Short?
)
