package io.github.bespaltovyj.interceptor

import mu.KLogging
import org.camunda.bpm.engine.delegate.DelegateExecution

import org.springframework.stereotype.Component
import ru.tinkoff.top.camunda.delegator.delegates.DelegateInformation
import ru.tinkoff.top.camunda.delegator.delegates.executors.interceptors.DelegateInterceptor

@Component
class CustomInterceptor : DelegateInterceptor() {

    companion object : KLogging()

    override fun execute(
        execution: DelegateExecution,
        delegateInfo: DelegateInformation,
        delegateArguments: Array<Any?>?
    ): Any? {
        try {
            logger.info { "Start execution delegate" }
            return nextExecutor.execute(execution, delegateInfo, delegateArguments).also {
                logger.info { "Finish execution delegate" }
            }
        } catch (ex: RuntimeException) {
            logger.error { "Delegate failed" }
            throw ex
        }
    }
}