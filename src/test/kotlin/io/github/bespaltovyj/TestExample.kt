package io.github.bespaltovyj

import io.github.bespaltovyj.delegate.*
import io.github.bespaltovyj.resolver.CustomResolver
import org.camunda.bpm.engine.ProcessEngineConfiguration
import org.camunda.bpm.engine.test.Deployment
import org.camunda.bpm.engine.test.ProcessEngineRule
import org.camunda.bpm.engine.test.assertions.cmmn.CmmnAwareTests.assertThat
import org.camunda.bpm.engine.test.mock.Mocks
import org.camunda.bpm.extension.process_test_coverage.junit.rules.ProcessCoverageInMemProcessEngineConfiguration
import org.camunda.bpm.extension.process_test_coverage.junit.rules.TestCoverageProcessEngineRuleBuilder
import org.junit.Before
import org.junit.ClassRule
import org.junit.Rule
import org.junit.Test
import ru.tinkoff.top.camunda.delegator.delegates.executors.DelegateExecutorImpl
import ru.tinkoff.top.camunda.delegator.delegates.executors.interceptors.ResolveArgumentsInterceptor
import ru.tinkoff.top.camunda.delegator.delegates.executors.interceptors.output.multiple.MultipleResultExecutionWriter
import ru.tinkoff.top.camunda.delegator.delegates.executors.interceptors.output.single.SingleResultExecutionWriter
import ru.tinkoff.top.camunda.delegator.delegates.resolvers.ContextVariableResolver
import ru.tinkoff.top.camunda.delegator.delegates.resolvers.DelegateExecutionResolver
import ru.tinkoff.top.camunda.delegator.delegates.resolvers.businesskey.BusinessKeyResolver
import ru.tinkoff.top.camunda.delegator.parser.DefaultDelegatorBpmnParseFactory
import ru.tinkoff.top.camunda.delegator.test.DelegateMethodHandlerMockRegister


class TestExample {

    companion object {

        private val register = DelegateMethodHandlerMockRegister(
            listOf(
                ResolveArgumentsInterceptor(
                    listOf(
                        CustomResolver(),
                        BusinessKeyResolver(),
                        DelegateExecutionResolver(),
                        ContextVariableResolver()
                    )
                ),
                SingleResultExecutionWriter(),
                MultipleResultExecutionWriter()
            ),
            DelegateExecutorImpl()
        )

        private val configuration: ProcessEngineConfiguration = ProcessCoverageInMemProcessEngineConfiguration().also {
            it.bpmnParseFactory = DefaultDelegatorBpmnParseFactory()
        }

        @Rule
        @ClassRule
        @JvmField
        val processEngineRule: ProcessEngineRule =
            TestCoverageProcessEngineRuleBuilder.create(configuration.buildProcessEngine()).build()
    }

    @Before
    fun setup() {
        register.initDelegates(
            listOf(
                DelegateWithCustomResolver(),
                TestDelegate(),
                JavaDelegateByDelegator()
            )
        )

        Mocks.register("camundaJavaDelegate", CamundaJavaDelegate())
    }

    @Test
    @Deployment(resources = ["demo.bpmn"])
    fun testTweetApproved() {
        val process = processEngineRule.processEngine.runtimeService.startProcessInstanceByKey("demo")

        assertThat(process).isStarted
        assertThat(process).hasPassed(
            "callJavaDelegate",
            "callDelegateByFullname",
            "callDelegateByAlias",
            "callDelegateWithCustomResolvers",
            "callJavaDelegateByDelegator"
        )
        assertThat(process).variables()
            .containsEntry("parsedText", "localVariableValue_parsed")
            .containsEntry("delegateResult", ResultDto(17))
            .containsEntry("delegatreResultFromAlias", ResultDto(117))
            .containsKey("randomResult")
            .containsEntry("resultFromDelegatorJavaDelegate", ResultDto(305))
        assertThat(process).isEnded
    }
}