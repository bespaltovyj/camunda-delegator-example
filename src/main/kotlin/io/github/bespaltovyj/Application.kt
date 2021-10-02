package io.github.bespaltovyj

import io.github.bespaltovyj.dto.ProcessResult
import org.camunda.bpm.engine.RuntimeService
import org.camunda.bpm.engine.runtime.ProcessInstanceWithVariables
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController


@SpringBootApplication
@RestController
@RequestMapping("/api")
class Application(
    private val runtimeService: RuntimeService
) {

    @PostMapping("/start")
    fun start(
        @RequestParam(defaultValue = "demo") processKey: String,
        @RequestParam businessKey: String?
    ): ProcessResult {
        val result = runtimeService.startProcessInstanceByKey(processKey, businessKey) as ProcessInstanceWithVariables

        return with(result) {
            ProcessResult(
                variables = variables,
                instainseId = processInstanceId,
                businessKey = businessKey
            )
        }

    }
}

fun main(args: Array<String>) {
    runApplication<Application>(*args)
}