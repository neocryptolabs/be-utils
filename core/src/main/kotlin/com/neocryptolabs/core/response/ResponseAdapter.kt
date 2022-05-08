package com.neocryptolabs.core.response

import javax.servlet.http.HttpServletRequest

class ResponseAdapter private constructor() {
    companion object {
        fun success(req: HttpServletRequest): ApiResponse {
            return toResult(req, null, null, true)
        }

        fun success(req: HttpServletRequest, count: Int): ApiResponse {
            return toResult(req, null, count, null, true)
        }

        fun success(req: HttpServletRequest, results: Any?): ApiResponse {
            return toResult(req, results, null, true)
        }

        fun fail(req: HttpServletRequest, e: Throwable): ApiResponse {
            return toResult(req, null, e, false)
        }

        fun internalServerError(req: HttpServletRequest, e: Throwable): ApiResponse {
            return toResult(req, null, e, false)
        }

        private fun toResult(req: HttpServletRequest, results: Any?, e: Throwable?, result: Boolean): ApiResponse {
            val count: Int = if (results == null) {
                0
            } else if (results is Collection<*>) {
                results.size
            } else {
                1
            }
            return toResult(req, results, count, e, result)
        }

        private fun toResult(req: HttpServletRequest, results: Any?, count: Int, e: Throwable?, result: Boolean) =
            if (e == null) {
                ApiResponse(
                    data = results,
                    count = count,
                    errors = "",
                    result = result,
                    request = ApiResponse.Request(
                        uri = req.requestURI,
                        method = req.method,
                        queryString = req.queryString
                    )
                )
            } else {
                ApiResponse(
                    data = results,
                    count = count,
                    errors = "${e?.javaClass?.simpleName} : ${e?.message}",
                    result = result,
                    request = ApiResponse.Request(
                        uri = req.requestURI,
                        method = req.method,
                        queryString = req.queryString
                    )
                )
            }
    }

    init {
        throw IllegalStateException("Static class")
    }
}