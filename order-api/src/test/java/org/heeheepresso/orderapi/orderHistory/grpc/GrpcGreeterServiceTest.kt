package org.heeheepresso.orderapi.orderHistory.grpc

import io.grpc.ManagedChannelBuilder
import io.grpc.ServerBuilder
import io.grpc.examples.helloworld.GreeterGrpcKt
import io.grpc.examples.helloworld.HelloRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.asExecutor
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class GrpcGreeterServiceTest {

    @Test
    fun `test sayHello`() {
        // Mock gRPC 서버 시작
        val server = ServerBuilder.forPort(0)
            .addService(GrpcGreeterService())
            .executor(Dispatchers.Default.asExecutor()) // 필요에 따라서 Executor를 설정해줍니다.
            .build()
            .start()

        val channel = ManagedChannelBuilder.forAddress("localhost", server.port)
            .usePlaintext()
            .build()

        val stub = GreeterGrpcKt.GreeterCoroutineStub(channel)

        // 서비스 호출 및 응답 확인
        runBlocking {
            val reply = stub.sayHello(HelloRequest.newBuilder().setName("Alice").build())
            println(reply)
            assertEquals("Hello Alice", reply.message)
        }

        // Mock gRPC 서버 종료
        server.shutdown()
    }
}

