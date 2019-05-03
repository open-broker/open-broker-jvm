package org.openbroker

import org.openbroker.cloudevents.CloudEvent
import org.openbroker.common.OpenBrokerEvent
import org.openbroker.common.serialize.parseJsonCloudEvent
import java.io.File

private const val JSON_PATH = "src/test/resources/examples"

internal fun loadJson(file: String): String {
    val fileWithExtension: String = if(file.endsWith(".json")) file else "$file.json"
    val completeFile = File("$JSON_PATH/$fileWithExtension")
    require(completeFile.exists()) { "File cannot be found: $completeFile" }
    return completeFile.readText()
}

internal inline fun <reified T: OpenBrokerEvent> parseJsonFromFile(file: String): CloudEvent<T> =
    parseJsonCloudEvent(loadJson(file))