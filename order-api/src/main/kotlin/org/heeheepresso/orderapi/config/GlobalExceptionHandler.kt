package org.heeheepresso.orderapi.config

import io.github.oshai.kotlinlogging.KotlinLogging
import io.grpc.*
import io.grpc.order.common.ErrorResponse
import net.devh.boot.grpc.server.interceptor.GrpcGlobalServerInterceptor
import org.heeheepresso.orderapi.common.ApiException

private val logger = KotlinLogging.logger {}

@GrpcGlobalServerInterceptor
class GlobalExceptionHandler : ServerInterceptor {

    override fun <ReqT : Any, RespT : Any> interceptCall(
        call: ServerCall<ReqT, RespT>,
        headers: Metadata,
        next: ServerCallHandler<ReqT, RespT>
    ): ServerCall.Listener<ReqT> {
        return next.startCall(ExceptionTranslatingServerCall(call), headers)
    }

    private class ExceptionTranslatingServerCall<ReqT, RespT>(
        delegate: ServerCall<ReqT, RespT>
    ) : ForwardingServerCall.SimpleForwardingServerCall<ReqT, RespT>(delegate) {

        override fun close(status: Status, trailers: Metadata) {
            if (status.isOk) {
                return super.close(status, trailers)
            }

            val cause = status.cause
            var newStatus = status
            var newtrailers = trailers

            logger.error(cause) { "Error handling gRPC endpoint." }

            if (status.code == Status.Code.UNKNOWN) {
                val translatedStatus = when (cause) {
                    is IllegalArgumentException -> Status.INVALID_ARGUMENT
                    is IllegalStateException -> Status.FAILED_PRECONDITION
                    is ApiException -> Status.INTERNAL
                    else -> Status.UNKNOWN
                }

                if (cause is ApiException) {
                    val errorResponse = ErrorResponse.newBuilder()
                        .apply {
                            code = cause.apiStatusResponsible.code
                            message = cause.apiStatusResponsible.message
                        }
                        .build()

                    val metadata = Metadata()
                    metadata.put(
                        Metadata.Key.of(ErrorResponse.getDescriptor().fullName, Metadata.ASCII_STRING_MARSHALLER),
                        errorResponse.toString()
                    )

                    newtrailers = metadata
                }

                newStatus = translatedStatus.withDescription(cause?.message).withCause(cause)
            }

            super.close(newStatus, newtrailers)
        }
    }


}