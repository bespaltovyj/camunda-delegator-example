package io.github.bespaltovyj.delegate

import io.github.bespaltovyj.resolver.RandomValue
import ru.tinkoff.top.camunda.delegator.annotations.CamundaDelegate
import ru.tinkoff.top.camunda.delegator.annotations.DelegateExecute
import ru.tinkoff.top.camunda.delegator.delegates.executors.interceptors.output.single.SingleResultVariable

@CamundaDelegate
class DelegateWithCustomResolver {

    @DelegateExecute
    @SingleResultVariable("result")
    fun returnRandom(
        @RandomValue random: Int
    ): Int {
        return random
    }
}