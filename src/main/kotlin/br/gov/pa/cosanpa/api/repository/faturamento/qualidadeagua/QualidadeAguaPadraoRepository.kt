package br.gov.pa.cosanpa.api.repository.faturamento.qualidadeagua

import br.gov.pa.cosanpa.api.dominio.faturamento.qualidadeagua.QualidadeAguaPadrao
import org.springframework.data.jpa.repository.JpaRepository

interface QualidadeAguaPadraoRepository: JpaRepository<QualidadeAguaPadrao, Int> {
}