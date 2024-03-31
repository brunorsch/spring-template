package brunorsch.template.spring.api

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import brunorsch.template.spring.api.dto.ErroResponse
import brunorsch.template.spring.exception.AppException
import brunorsch.template.spring.support.i18n.I18nService
import java.lang.Exception

@ControllerAdvice
class ControllerExceptionHandler(
    internal val i18nService: I18nService
) {
    @ExceptionHandler
    fun handleAppException(appException: AppException): ResponseEntity<ErroResponse> {
        val response = ErroResponse(
            codigoErro = appException.codigoErro.name,
            mensagem = i18nService.get(appException.mensagemErro.path),
            detalhes = appException.detalhes
        )

        return ResponseEntity(response, appException.codigoErro.status)
    }
}