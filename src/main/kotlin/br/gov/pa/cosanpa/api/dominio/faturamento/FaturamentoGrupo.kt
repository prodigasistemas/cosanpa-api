package br.gov.pa.cosanpa.api.dominio.faturamento

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "faturamento_grupo", schema = "faturamento")
data class FaturamentoGrupo(
    @Id
    @Column(name = "ftgr_id")
    val id: Int,
    @Column(name = "ftgr_amreferencia")
    val referencia: Int?
)
