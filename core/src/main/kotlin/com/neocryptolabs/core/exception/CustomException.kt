package com.neocryptolabs.core.exception

open class CustomException : RuntimeException {
    constructor(message: String) : super(message)
    constructor(message: String?, cause: Throwable) : super(message, cause)
    constructor(cause: Throwable) : super(cause)
}