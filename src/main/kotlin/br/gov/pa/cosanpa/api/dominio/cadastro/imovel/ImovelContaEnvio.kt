package br.gov.pa.cosanpa.api.dominio.cadastro.imovel

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "imovel_conta_envio", schema = "cadastro")
data class ImovelContaEnvio(
    @Id
    @Column(name = "icte_id")
    val id: Int,
    @Column(name = "icte_dscontaenvio")
    val descricao: String,
    @Column(name = "icte_icclienteresponsavel")
    val indicadorClienteResponsavel: Short
)
{
    companion object{
        const val ENVIAR_CLIENTE_RESPONSAVEL: Short = 1
        const val ENVIAR_IMOVEL: Short = 2
        const val NAO_PAGAVEL_IMOVEL_PAGAVEL_RESPONSAVEL: Short = 3
        const val ENVIAR_PARA_EMAIL: Short = 4
        const val ENVIAR_PARA_IMOVEL_E_PARA_EMAIL: Short = 5
        const val ENVIAR_CONTA_BRAILLE: Short = 6
        const val ENVIAR_CONTA_BRAILLE_RESPONSAVEL: Short = 7
        const val ENVIAR_CLIENTE_RESPONSAVEL_FINAL_GRUPO: Short = 9
    }
}