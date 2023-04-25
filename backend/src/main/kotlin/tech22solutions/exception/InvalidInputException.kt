package tech22solutions.exception

import io.ktor.http.*
import org.apache.http.HttpException

class InvalidInputException(message: String,status: HttpStatusCode) : HttpException(message) {
}