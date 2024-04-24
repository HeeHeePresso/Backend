package org.heeheepresso.gateway.common.exception

import jakarta.servlet.http.HttpServletRequest
import mu.KotlinLogging
import org.heeheepresso.gateway.common.response.GatewayResponse
import org.heeheepresso.gateway.common.response.StatusCode
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {

    private val logger = KotlinLogging.logger {}

    @ExceptionHandler(GatewayException::class)
    fun handleGatewayException(e: GatewayException, request: HttpServletRequest)
            : ResponseEntity<GatewayResponse<Void>> {
        logger.warn { "Gateway Exception from ${request.requestURI}\n$e" }
        return ResponseEntity.status(e.httpStatus)
                .body(GatewayResponse(e.statusCode))
    }

    @ExceptionHandler(Exception::class)
    fun handleServerException(e: Exception, request: HttpServletRequest)
            : ResponseEntity<GatewayResponse<Void>> {
        logger.error { "Server Exception from ${request.requestURI}\n$e" }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(GatewayResponse(StatusCode.SERVER_ERROR))
    }


}