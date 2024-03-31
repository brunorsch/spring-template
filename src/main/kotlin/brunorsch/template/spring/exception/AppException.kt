package brunorsch.template.spring.exception

import brunorsch.template.spring.support.erros.CodigoErro
import brunorsch.template.spring.support.erros.MensagemErro

class AppException(
    val codigoErro: CodigoErro,
    val mensagemErro: MensagemErro,
    mensagemContexto: String? = null,
    val detalhes: Map<String, Any>? = null,
    override val cause: Throwable? = null,
) : RuntimeException(mensagemContexto ?: codigoErro.name, cause, false, false)