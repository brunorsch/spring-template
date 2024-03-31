package brunorsch.template.spring.config

import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import org.springframework.boot.info.BuildProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class SwaggerConfig(val buildProperties: BuildProperties) {
    @Bean
    fun openApi(): OpenAPI {
        return OpenAPI()
            .info(
                Info()
                    .title("Spring Template")
                    .description("Template para serviços feitos com Spring e Kotlin")
                    .version(buildProperties.version)
            )
    }
}