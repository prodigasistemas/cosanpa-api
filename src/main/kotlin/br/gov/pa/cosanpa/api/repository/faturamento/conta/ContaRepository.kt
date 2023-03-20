package br.gov.pa.cosanpa.api.repository.faturamento.conta

import br.gov.pa.cosanpa.api.dominio.faturamento.conta.Conta
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface ContaRepository: JpaRepository<Conta, Int> {

    @Query(
        value = "SELECT gerenciaRegional.nome, " +
                " localidade.id, localidade.descricao, " +
                " clienteUsuario.nome, " +
                " conta.codigoSetorComercial, conta.quadra, " +
                " conta.lote, conta.sublote, " +
                " clienteResposanvel.id, clienteResposanvel.nome, " +
                " ligacaoAguaSituacao.id, ligacaoEsgotoSituacao.id, " +
                " conta.dataVencimento, conta.dataValidade, " +
                " conta.digitoVerificador, conta.percentualEsgoto, " +
                " imovelPerfil.id, consumoTarifa.id, conta.id, conta.referencia, " +
                " gerenciaRegional.id, " +
                " ligacaoAguaSituacao.indicadorFaturamentoSituacao, " +
                " ligacaoEsgotoSituacao.indicadorFaturamentoSituacao, " +
                " ice.id, " +
                " clienteUsuario.cpf, " +
                " clienteUsuario.cnpj " +
                " FROM Conta conta " +
                " INNER JOIN conta.imovel imovel " +
                " INNER JOIN conta.debitoCreditoSituacaoAtual debitoCreditoSituacaoAtual " +
                " INNER JOIN conta.localidade localidade " +
                " INNER JOIN localidade.gerenciaRegional gerenciaRegional " +
                " INNER JOIN conta.ligacaoAguaSituacao ligacaoAguaSituacao " +
                " INNER JOIN conta.ligacaoEsgotoSituacao ligacaoEsgotoSituacao " +
                " INNER JOIN conta.imovelPerfil imovelPerfil " +
                " INNER JOIN conta.consumoTarifa consumoTarifa " +
                " LEFT JOIN conta.clienteContas clienteContasUsuario WITH " +
                " (clienteContasUsuario.clienteRelacaoTipo.id = :idClienteRelacaoTipoUsuario) " +
                " LEFT JOIN clienteContasUsuario.cliente clienteUsuario " +
                " LEFT JOIN conta.clienteContas clienteContasReposanvel WITH " +
                " (clienteContasReposanvel.clienteRelacaoTipo.id = :idClienteRelacaoTipoResponsavel)" +
                " LEFT JOIN clienteContasReposanvel.cliente clienteResposanvel " +
                " LEFT JOIN imovel.imovelContaEnvio ice " +
                " LEFT JOIN conta.faturamentoGrupo fg " +
                " WHERE imovel.id = :idImovel AND " +
                "  conta.referencia = :anoMesReferencia " +
                " AND debitoCreditoSituacaoAtual.id = :preFaturada " +
                " and not exists ( from MovimentoContaPrefaturada mcpf where mcpf.anoMesReferenciaPreFaturamento = fg.referencia and imovel.id = mcpf.imovel.id  )"
    )
    fun obterContaGerarDados(
        idImovel: Int,
        anoMesReferencia: Int,
        preFaturada: Int,
        idClienteRelacaoTipoUsuario: Int,
        idClienteRelacaoTipoResponsavel: Int
    )
}