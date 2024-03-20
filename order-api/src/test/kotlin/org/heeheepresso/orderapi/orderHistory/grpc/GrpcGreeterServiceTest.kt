package org.heeheepresso.orderapi.orderHistory.grpc

import io.grpc.ManagedChannelBuilder
import io.grpc.ServerBuilder
import io.grpc.examples.helloworld.GreeterGrpcKt
import io.grpc.examples.helloworld.HelloRequest
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.asExecutor
import kotlinx.coroutines.runBlocking


class GrpcGreeterServiceTest2 : BehaviorSpec({
    given("a gRPC greeter service") {
        val server = ServerBuilder.forPort(0)
            .addService(GrpcGreeterService())
            .executor(Dispatchers.Default.asExecutor())
            .build()
            .start()

        try {
            val channel = ManagedChannelBuilder.forAddress("localhost", server.port)
                .usePlaintext()
                .build()
            val stub = GreeterGrpcKt.GreeterCoroutineStub(channel)
            val name = "Tester"

            When("sayHello를 호출하면") {
                val reply = runBlocking {
                    stub.sayHello(HelloRequest.newBuilder().setName(name).build())
                }

                Then("request에 담은 메시지를 넘긴다") {
                    reply.message shouldBe "Hello $name"
                }
            }
        } catch (_: Exception) {
        } finally {
            server.shutdown()
        }

    }
})
