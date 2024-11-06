package com.sebas.interrapidismo.commons

open class Exceptions(
    cause: Throwable? = null
): Exception(null,cause)

class NetworkFailedResponseException: Exceptions()

fun Throwable.toExceptions(): Exceptions {
    return when(this) {
        is Exceptions -> this
        else -> Exceptions(cause = this)
    }
}