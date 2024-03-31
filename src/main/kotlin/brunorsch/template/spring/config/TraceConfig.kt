package brunorsch.template.spring.config

import brave.Tracing
import brave.TracingCustomizer
import brave.handler.SpanHandler
import brave.propagation.CurrentTraceContext
import brave.propagation.Propagation
import brave.sampler.Sampler
import org.springframework.boot.actuate.autoconfigure.tracing.TracingProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.env.Environment
import java.util.function.Consumer


@Configuration
class TraceConfig {
    @Bean
    fun braveTracing(
        environment: Environment,
        properties: TracingProperties,
        spanHandlers: List<SpanHandler?>,
        tracingCustomizers: List<TracingCustomizer>,
        currentTraceContext: CurrentTraceContext?,
        propagationFactory: Propagation.Factory?,
        sampler: Sampler?
    ): Tracing {
        val applicationName: String = environment.getProperty("spring.application.name", "AppName")

        val builder = Tracing.newBuilder()
            .currentTraceContext(currentTraceContext)
            .traceId128Bit(false)
            .supportsJoin(properties.brave.isSpanJoiningSupported)
            .propagationFactory(propagationFactory)
            .sampler(sampler)
            .localServiceName(applicationName)

        spanHandlers.forEach(Consumer { spanHandler: SpanHandler? ->
            builder.addSpanHandler(
                spanHandler
            )
        })

        for (tracingCustomizer in tracingCustomizers) {
            tracingCustomizer.customize(builder)
        }

        return builder.build()
    }
}