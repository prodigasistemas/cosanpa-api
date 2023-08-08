package br.gov.pa.cosanpa.api.dominio.micromedicao.hidrometro

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "hidrometro_local_inst", schema = "micromedicao")
data class HidrometroLocalInstalacao(
    @Id
    @Column(name = "hili_id")
    val id: Int,
    @Column(name = "hili_dshidmtlocalinstalacao")
    val descricao: String
)
