package io.github.bespaltovyj.handler

import ru.tinkoff.top.camunda.delegator.delegates.DelegateInformation
import ru.tinkoff.top.camunda.delegator.delegates.MethodHandlerDelegateImpl
import ru.tinkoff.top.camunda.delegator.delegates.executors.DelegateExecutor
import ru.tinkoff.top.camunda.delegator.delegates.factory.MethodHandlerDelegateFactory
import ru.tinkoff.top.camunda.delegator.delegates.factory.MethodHandlerDelegateFactoryImpl
import ru.tinkoff.top.camunda.delegator.servicetask.MethodHandlerDelegate

class HandlerFactory: MethodHandlerDelegateFactory {
    override fun createMethodHandlerDelegate(
        delegateInformation: DelegateInformation,
        delegateExecutor: DelegateExecutor
    ): MethodHandlerDelegate {
        return TestMethodHandlerDelegateImpl(
            delegateInformation = delegateInformation,
            delegateExecutor = delegateExecutor
        )
    }
}