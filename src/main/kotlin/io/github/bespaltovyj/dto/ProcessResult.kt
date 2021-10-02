package io.github.bespaltovyj.dto

data class ProcessResult(
    val variables: MutableMap<String, Any>,
    val instainseId: String,
    val businessKey: String?
)