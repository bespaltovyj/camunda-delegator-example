package io.github.bespaltovyj.resolver

import org.camunda.bpm.engine.delegate.DelegateExecution
import org.springframework.core.MethodParameter
import org.springframework.stereotype.Component
import ru.tinkoff.top.camunda.delegator.delegates.resolvers.DelegateArgumentResolver
import ru.tinkoff.top.camunda.delegator.delegates.resolvers.businesskey.BusinessKey
import ru.tinkoff.top.camunda.delegator.delegates.resolvers.getAnnotation
import kotlin.random.Random

@Target(AnnotationTarget.VALUE_PARAMETER, AnnotationTarget.ANNOTATION_CLASS)
@Retention(AnnotationRetention.RUNTIME)
annotation class RandomValue

@Component
class CustomResolver : DelegateArgumentResolver {
    override fun supportsParameter(methodParameter: MethodParameter): Boolean {
        return methodParameter.getAnnotation<RandomValue>() != null
    }

    override fun resolveArgument(
        delegateExecution: DelegateExecution,
        methodParameter: MethodParameter
    ): Int {
        return Random.nextInt(0, 100)
    }
}