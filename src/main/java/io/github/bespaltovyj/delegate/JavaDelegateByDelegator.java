package io.github.bespaltovyj.delegate;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.springframework.lang.Nullable;
import ru.tinkoff.top.camunda.delegator.annotations.CamundaDelegate;
import ru.tinkoff.top.camunda.delegator.annotations.DelegateExecute;
import ru.tinkoff.top.camunda.delegator.annotations.Variable;
import ru.tinkoff.top.camunda.delegator.delegates.executors.interceptors.output.single.SingleResultVariable;
import ru.tinkoff.top.camunda.delegator.delegates.resolvers.DelegateExecutionAnn;
import ru.tinkoff.top.camunda.delegator.delegates.resolvers.businesskey.BusinessKey;

import java.util.Optional;

@CamundaDelegate
public class JavaDelegateByDelegator {


    @DelegateExecute
    @SingleResultVariable(name = "result")
    public ResultDto returnVariable(
            @DelegateExecutionAnn DelegateExecution execution,
            @Variable(name = "required") Integer required,
            // для опциональных полей можно использовать любую @Nullable аннотацию с Runtime policy
            // или использовать класс Optional
            @Variable(name = "optional") @Nullable String optionalWithAnnotation,
            @Variable(name = "optional2") @Nullable Optional<String> optional,
            @BusinessKey @Nullable String businessKey
    ) {
        return new ResultDto(required + 100);
    }
}
