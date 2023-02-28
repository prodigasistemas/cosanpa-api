package br.gov.pa.cosanpa.api.domain.leiturista

import br.gov.pa.cosanpa.api.domain.empresa.Empresa
import br.gov.pa.cosanpa.api.domain.funcionario.Funcionario
import br.gov.pa.cosanpa.api.domain.usuario.Usuario
import jakarta.persistence.*

@Entity
@Table(name = "leiturista", schema = "micromedicao")
data class Leiturista(
    @Id
    @Column(name = "leit_id")
    val id: Int? = null,

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "func_id")
    val funcionario: Funcionario,
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "empr_id")
    val empresa: Empresa,
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "usur_id")
    val usuario: Usuario

)
