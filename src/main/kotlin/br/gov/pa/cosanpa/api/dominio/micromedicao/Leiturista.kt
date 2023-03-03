package br.gov.pa.cosanpa.api.dominio.micromedicao

import br.gov.pa.cosanpa.api.dominio.cadastro.Empresa
import br.gov.pa.cosanpa.api.dominio.seguranca.Usuario
import jakarta.persistence.*

@Entity
@Table(name = "leiturista", schema = "micromedicao")
data class Leiturista(
    @Id
    @Column(name = "leit_id")
    val id: Int = 0,

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "empr_id")
    val empresa: Empresa,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usur_id")
    val usuario: Usuario

)
