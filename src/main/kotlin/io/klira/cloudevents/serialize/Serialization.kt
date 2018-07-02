package io.klira.cloudevents.serialize

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import io.klira.cloudevents.CloudEvent

fun <T> jsonString(e: T): String = jacksonObjectMapper().writeValueAsString(e)

inline fun <reified T: Any> cloudEvent(payload: String): CloudEvent<T> {
    return jacksonObjectMapper().readValue(payload)
}