package com.neocryptolabs.core.response

data class ApiResponse(
    val data: Any?,
    val count: Int,
    val errors: String,
    val result: Boolean,
    val request: Request
) {
    data class Request(
        val uri: String?,
        val queryString: String?,
        val method: String?
    )
}