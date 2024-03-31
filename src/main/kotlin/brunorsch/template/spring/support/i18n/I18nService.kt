package brunorsch.template.spring.support.i18n

import org.springframework.context.MessageSource
import org.springframework.stereotype.Service
import java.util.*

@Service
class I18nService(val source: MessageSource) {
    fun get(codigoMensagem: String, vararg args: String?): String {
        return source.getMessage(codigoMensagem, args, Locale.getDefault())
    }
}