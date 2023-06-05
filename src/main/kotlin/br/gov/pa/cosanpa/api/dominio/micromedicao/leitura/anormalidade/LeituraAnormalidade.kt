package br.gov.pa.cosanpa.api.dominio.micromedicao.leitura.anormalidade

import jakarta.persistence.*

@Entity
@Table(name = "leitura_anormalidade", schema = "micromedicao")
data class LeituraAnormalidade(
    @Id
    @Column(name = "ltan_id")
    val id: Int,
    @Column(name = "ltan_dsleituraanormalidade")
    val descricao: String
)
