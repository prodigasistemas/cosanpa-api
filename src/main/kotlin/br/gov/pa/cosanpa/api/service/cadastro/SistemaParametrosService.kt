package br.gov.pa.cosanpa.api.service.cadastro

import br.gov.pa.cosanpa.api.dominio.cadastro.SistemaParametros
import br.gov.pa.cosanpa.api.repository.cadastro.sistemaparametros.SistemaParametrosRepository
import org.springframework.stereotype.Service

@Service
class SistemaParametrosService(
    private val repository: SistemaParametrosRepository
) {

    fun retornaParametrosDoSistema() : SistemaParametros = repository.findById(SistemaParametros.ID).get()
}