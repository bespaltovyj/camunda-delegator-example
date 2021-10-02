package io.github.bespaltovyj.delegate;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Service;

@Service
public class CamundaJavaDelegate implements JavaDelegate {

    @Override
    public void execute(DelegateExecution execution) {
        String variable = (String) execution.getVariableLocal("localVariable");

        execution.setVariableLocal("localVariableResult", variable + "_parsed");
    }
}
