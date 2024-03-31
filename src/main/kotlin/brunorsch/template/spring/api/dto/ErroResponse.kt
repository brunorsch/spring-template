package brunorsch.template.spring.api.dto

data class ErroResponse(
    val codigoErro: String,
    val mensagem: String,
    val detalhes: Map<String, Any>? = null
)