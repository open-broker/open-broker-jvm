package org.openbroker.no.privateunsecuredloan.model

data class Message(
    val message: String,
    val requiresAction: Boolean
)