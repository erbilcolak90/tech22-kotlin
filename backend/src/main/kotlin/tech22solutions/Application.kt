package tech22solutions

import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.config.*

import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.routing.*
import tech22solutions.plugins.*
import tech22solutions.service.ExchangeRateService
import kotlinx.serialization.json.*

fun main(args: Array<String>): Unit =
    io.ktor.server.tomcat.EngineMain.main(args)


@Suppress("unused") // application.conf references the main function. This annotation prevents the IDE from marking it as unused.
fun Application.module() {
    install(ContentNegotiation) {
       json()
    }
    configureMonitoring()
    configureRouting(exchangeRateService = ExchangeRateService())

}

