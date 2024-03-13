package kmp.ktor.retries.demo

import SERVER_PORT
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

var callCount = 1

fun main() {
    embeddedServer(Netty, port = SERVER_PORT, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    routing {
        get("/") {
            if(callCount % 4 == 0) {
                call.respondText("Hello from the server!")
            } else {
                call.respond(HttpStatusCode.InternalServerError, "Sample error")
            }
            callCount++
        }
    }
}