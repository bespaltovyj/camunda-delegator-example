package io.github.bespaltovyj.delegate

import mu.KLogging
import net.logstash.logback.argument.StructuredArguments.keyValue
import org.camunda.bpm.engine.delegate.DelegateExecution
import ru.tinkoff.top.camunda.delegator.annotations.CamundaDelegate
import ru.tinkoff.top.camunda.delegator.annotations.DelegateAliases
import ru.tinkoff.top.camunda.delegator.annotations.DelegateExecute
import ru.tinkoff.top.camunda.delegator.annotations.Variable
import ru.tinkoff.top.camunda.delegator.delegates.executors.interceptors.output.single.SingleResultVariable
import ru.tinkoff.top.camunda.delegator.delegates.resolvers.DelegateExecutionAnn
import ru.tinkoff.top.camunda.delegator.delegates.resolvers.businesskey.BusinessKey

@CamundaDelegate
class TestDelegate {

    companion object : KLogging()

    @DelegateExecute
    @DelegateAliases("aliasExample")
    @SingleResultVariable("result")
    fun returnVariable(
        @DelegateExecutionAnn execution: DelegateExecution,
        @Variable("required") required: Int,
        @Variable("optional") optional: String?,
        @BusinessKey businessKey: String?
    ): ResultDto {
        logger.info(
            "Log required variable",
            keyValue("requiredVariable", required)
        )
        return ResultDto(required + 10)
    }
}