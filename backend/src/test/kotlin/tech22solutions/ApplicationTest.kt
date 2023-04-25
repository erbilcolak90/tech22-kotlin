package tech22solutions

import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.server.testing.*
import kotlin.test.*
import io.ktor.http.*
import tech22solutions.plugins.*
import tech22solutions.service.ExchangeRateService

class ApplicationTest {
    @Test
    fun testRoot() = testApplication {
        application {
            //configureRouting()
        }
        client.get("/").apply {
            assertEquals(HttpStatusCode.OK, status)
            assertEquals("Hello World!", bodyAsText())
        }
    }
}
