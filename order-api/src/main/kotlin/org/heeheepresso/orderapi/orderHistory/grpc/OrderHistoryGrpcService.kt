package org.heeheepresso.orderapi.orderHistory.grpc

import io.grpc.examples.helloworld.GreeterGrpcKt
import io.grpc.examples.helloworld.HelloReply
import io.grpc.examples.helloworld.HelloRequest
import net.devh.boot.grpc.server.service.GrpcService
import org.heeheepresso.orderapi.common.ApiException
import org.heeheepresso.orderapi.common.ApiStatus

import kotlin.coroutines.EmptyCoroutineContext

@GrpcService
class GrpcGreeterService : GreeterGrpcKt.GreeterCoroutineImplBase(
    coroutineContext = EmptyCoroutineContext
) {
    override suspend fun sayHello(request: HelloRequest): HelloReply {
        if (request.name == "error") throw ApiException(ApiStatus.FAIL)
        return HelloReply.newBuilder()
            .apply {
                message = "Hello ${request.name}"
            }
            .build()
    }

}