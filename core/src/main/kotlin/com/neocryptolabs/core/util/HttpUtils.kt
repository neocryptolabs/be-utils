package com.neocryptolabs.core.util

import com.neocryptolabs.core.exception.CustomException
import com.neocryptolabs.core.exception.UserNotFoundException
import com.neocryptolabs.core.response.ResponseAdapter
import com.google.gson.JsonSyntaxException
import org.slf4j.Logger
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.client.RestClientException
import javax.servlet.http.HttpServletRequest
import kotlin.reflect.full.declaredMemberProperties

fun buildPathParameter(instance: Any) =
    StringBuilder().apply {
        var next = false
        for (member in instance::class.declaredMemberProperties) {
            try {
                member.getter.call(instance)
            } catch (e: Exception) {
                continue
            }
            if (next) {
                append("&")
            } else {
                next = true
            }
            append(member.name)
            append("=")
            append(member.getter.call(instance))
        }
    }.toString()

fun generalResponse(service: () -> Any?, logger: Logger, req: HttpServletRequest): ResponseEntity<Any> {
    return try {
        val result = service()
        val res = ResponseAdapter.success(req, result)
        ResponseEntity.status(HttpStatus.OK).body(res)
    } catch (e: IllegalArgumentException) {
        logger.debug(String.format("Requirements are insufficient."), e)
        val res = ResponseAdapter.fail(req, e)
        ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res)
    } catch (e: NoSuchElementException) {
        logger.debug(String.format("Not found provider."), e)
        val res = ResponseAdapter.fail(req, e)
        ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res)
    } catch (e: IllegalAccessException) {
        val res = ResponseAdapter.fail(req, e)
        ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res)
    } catch (e: UserNotFoundException) {
        logger.debug(String.format("Not found user."), e)
        val res = ResponseAdapter.success(req)
        ResponseEntity.status(HttpStatus.OK).body(res)
    } catch (e: RestClientException) {
        val res = ResponseAdapter.fail(req, e)
        ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(res)
    } catch (e: JsonSyntaxException) {
        val res = ResponseAdapter.internalServerError(req, e)
        logger.error("Catch JsonSyntaxException ", e)
        ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res)
    } catch (e: UnsupportedOperationException) {
        val res = ResponseAdapter.internalServerError(req, e)
        logger.error("Catch UnsupportedOperationException ", e)
        ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res)
    } catch (e: CustomException) {
        val res = ResponseAdapter.fail(req, e)
        ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res)
    } catch (e: Exception) {
        logger.error(String.format("Check internal logic."), e)
        val res = ResponseAdapter.internalServerError(req, e)
        ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res)
    }
}

fun <T> generalResponse(
    service: (T) -> Any?,
    param: T,
    logger: Logger,
    req: HttpServletRequest
): ResponseEntity<Any> {
    return try {
        val result = service(param)
        val res = ResponseAdapter.success(req, result)
        ResponseEntity.status(HttpStatus.OK).body(res)
    } catch (e: IllegalArgumentException) {
        logger.debug(String.format("Requirements are insufficient. parameter: %s", param), e)
        val res = ResponseAdapter.fail(req, e)
        ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res)
    } catch (e: NoSuchElementException) {
        logger.debug(String.format("Not found provider. parameter: '%s'", param), e)
        val res = ResponseAdapter.fail(req, e)
        ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res)
    } catch (e: IllegalAccessException) {
        val res = ResponseAdapter.fail(req, e)
        ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res)
    } catch (e: UserNotFoundException) {
        logger.debug(String.format("Not found user. param: '%s'", param), e)
        val res = ResponseAdapter.success(req)
        ResponseEntity.status(HttpStatus.OK).body(res)
    } catch (e: RestClientException) {
        val res = ResponseAdapter.fail(req, e)
        logger.error("Catch RestClientException", e)
        ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(res)
    } catch (e: JsonSyntaxException) {
        val res = ResponseAdapter.internalServerError(req, e)
        logger.error("Catch JsonSyntaxException ", e)
        ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res)
    } catch (e: UnsupportedOperationException) {
        val res = ResponseAdapter.internalServerError(req, e)
        logger.error("Catch UnsupportedOperationException ", e)
        ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res)
    } catch (e: CustomException) {
        val res = ResponseAdapter.fail(req, e)
        ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res)
    } catch (e: Exception) {
        logger.error(String.format("Check internal logic. parameter: '%s'", param), e)
        val res = ResponseAdapter.internalServerError(req, e)
        ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res)
    }
}

fun <T, U> generalResponse(
    service: (T, U) -> Any?,
    param1: T,
    param2: U,
    logger: Logger,
    req: HttpServletRequest
): ResponseEntity<Any> {
    return try {
        val result = service(param1, param2)
        val res = ResponseAdapter.success(req, result)
        ResponseEntity.status(HttpStatus.OK).body(res)
    } catch (e: IllegalArgumentException) {
        logger.debug(String.format("Requirements are insufficient. parameter1: %s, parameter2: %s", param1, param2), e)
        val res = ResponseAdapter.fail(req, e)
        ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res)
    } catch (e: NoSuchElementException) {
        logger.debug(String.format("Not found provider. parameter1: '%s', parameter2: %s", param1, param2), e)
        val res = ResponseAdapter.fail(req, e)
        ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res)
    } catch (e: IllegalAccessException) {
        val res = ResponseAdapter.fail(req, e)
        ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res)
    } catch (e: UserNotFoundException) {
        logger.debug(String.format("Not found user. parameter1: '%s', parameter2: %s", param1, param2), e)
        val res = ResponseAdapter.success(req)
        ResponseEntity.status(HttpStatus.OK).body(res)
    } catch (e: RestClientException) {
        val res = ResponseAdapter.fail(req, e)
        ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(res)
    } catch (e: JsonSyntaxException) {
        val res = ResponseAdapter.internalServerError(req, e)
        logger.error("Catch JsonSyntaxException ", e)
        ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res)
    } catch (e: UnsupportedOperationException) {
        val res = ResponseAdapter.internalServerError(req, e)
        logger.error("Catch UnsupportedOperationException ", e)
        ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res)
    } catch (e: CustomException) {
        val res = ResponseAdapter.fail(req, e)
        ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res)
    } catch (e: Exception) {
        logger.error(String.format("Check internal logic. parameter1: '%s', parameter2: %s", param1, param2), e)
        val res = ResponseAdapter.internalServerError(req, e)
        ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res)
    }
}