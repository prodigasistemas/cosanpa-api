package br.gov.pa.cosanpa.api.repository.cadastro.sistemaparametros

import br.gov.pa.cosanpa.api.dominio.cadastro.SistemaParametros
import org.springframework.data.jpa.repository.JpaRepository

interface SistemaParametrosRepository : JpaRepository<SistemaParametros, Int> {

}
