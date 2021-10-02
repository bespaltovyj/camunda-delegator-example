package io.github.bespaltovyj.handler

import org.camunda.bpm.engine.delegate.DelegateExecution
import org.slf4j.MDC
import ru.tinkoff.top.camunda.delegator.delegates.DelegateInformation
import ru.tinkoff.top.camunda.delegator.delegates.MethodHandlerDelegateImpl
import ru.tinkoff.top.camunda.delegator.delegates.executors.DelegateExecutor


private const val PROCESS_ENGINE = "processEngine"

class TestMethodHandlerDelegateImpl(
    delegateInformation: DelegateInformation,
    delegateExecutor: DelegateExecutor
) : MethodHandlerDelegateImpl(delegateInformation, delegateExecutor) {

    override fun execute(execution: DelegateExecution) {
        try {
            MDC.put(PROCESS_ENGINE, execution.processEngine.name)
            super.execute(execution)
        } finally {
            MDC.remove(PROCESS_ENGINE)
        }
    }
}