package tech22solutions.plugins

import io.ktor.http.*
import io.ktor.server.routing.*
import io.ktor.server.response.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import tech22solutions.exception.InvalidInputException
import tech22solutions.input.ExchangeRateInput
import tech22solutions.service.ExchangeRateService

fun Application.configureRouting(exchangeRateService: ExchangeRateService) {
    routing {
        post("/exchange") {

            try {
                val input = call.receive<ExchangeRateInput>()
                // if input is validate
                if (input.isValid()) {
                    val exchangeRatePayload = exchangeRateService.getExchange(input)

                    if (exchangeRatePayload != null) {
                        call.respond(exchangeRatePayload)
                    } else {
                        call.respond(
                            HttpStatusCode.InternalServerError,
                            "An error occurred while fetching exchange rate data."
                        )
                    }
                } else {
                    // input is not validiate
                    throw InvalidInputException("Invalid input provided.", HttpStatusCode.BadRequest)
                }

            }
            catch (exception: Exception) {
                call.respond(HttpStatusCode.BadRequest, "Invalid input provided.")
            }

        }

    }
}
