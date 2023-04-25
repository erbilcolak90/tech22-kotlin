package tech22solutions

import org.junit.Test
import org.junit.Assert.*
import tech22solutions.input.ExchangeRateInput
import tech22solutions.service.ExchangeRateService

@kotlinx.serialization.Serializable
class ExchangeRateServiceTest {

    val baseUrl = "https://api.apilayer.com/fixer/latest?"
    val apiKey = "VrpPC0mQj0T6VmqPfexRU2ntidngbgc8"

    @Test
    fun `should return null when API returns non-200 status code`() {
        // Arrange
        val input = ExchangeRateInput("USD", "INVALID", 1.0)
        val service = ExchangeRateService(baseUrl, apiKey)

        // Act
        val result = service.getExchange(input)

        // Assert
        assertNull(result)
    }

    @Test
    fun `should return ExchangeRatePayload when API returns 200 status code`() {
        // Arrange
        val input = ExchangeRateInput("USD", "EUR", 1.0)
        val service = ExchangeRateService(baseUrl = "https://api.exchangeratesapi.io/latest", apiKey = "")

        // Act
        val result = service.getExchange(input)

        // Assert
        assertNotNull(result)
        assertTrue(result!!.amount > 0)
        assertEquals(input.baseCurrency, result.base)
        assertEquals(input.targetCurrency, result.rates.keys.firstOrNull())
    }

}
