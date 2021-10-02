package io.github.bespaltovyj.configuration

import io.github.bespaltovyj.handler.HandlerFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import ru.tinkoff.top.camunda.delegator.delegates.factory.MethodHandlerDelegateFactory

@Configuration
class Configuration {

    @Bean
    fun methodHandlerDelegateFactory(): MethodHandlerDelegateFactory {
        return HandlerFactory()
    }
}