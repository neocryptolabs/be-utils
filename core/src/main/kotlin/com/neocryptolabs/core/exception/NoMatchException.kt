package com.neocryptolabs.core.exception

class NoMatchException : CustomException {
    constructor(message: String) : super(message)
    constructor(message: String?, cause: Throwable) : super(message, cause)
    constructor(cause: Throwable) : super(cause)
}