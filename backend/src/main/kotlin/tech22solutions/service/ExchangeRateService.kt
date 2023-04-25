package tech22solutions.service

import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import org.apache.http.client.methods.HttpGet
import org.apache.http.impl.client.HttpClients
import tech22solutions.exception.CustomException
import tech22solutions.input.ExchangeRateInput
import tech22solutions.payload.ExchangeRatePayload

@Serializable
class ExchangeRateService(
    private val baseUrl: String = "https://api.apilayer.com/fixer/latest?",
    private val apiKey: String = "VrpPC0mQj0T6VmqPfexRU2ntidngbgc8"
) {

    fun getExchange(exchangeRateInput: ExchangeRateInput): ExchangeRatePayload {

        val url: String =
            baseUrl + "symbols=" + exchangeRateInput.targetCurrency + "&base=" + exchangeRateInput.baseCurrency
        val httpClient = HttpClients.createDefault()
        val httpGet = HttpGet(url)

        httpGet.addHeader("apikey", apiKey)

        try {
            val httpResponse = httpClient.execute(httpGet)

            val responseBody = httpResponse.entity.content.bufferedReader().use { it.readText() }
            // response kullanıldıktan sonra kapatılmalı
            httpResponse.close()

            if (httpResponse.statusLine.statusCode == 200) {

                val exchangeRateResponse =
                    Json { ignoreUnknownKeys = true }.decodeFromString<ExchangeRatePayload>(responseBody)
         /*       val targetCurrencyInput = exchangeRateInput.targetCurrency
                //val amountInput =*/

                val ratesMap = exchangeRateResponse.rates
                val targetCurrencyPayload = ratesMap.get(exchangeRateInput.targetCurrency)

                //val convertAmount = exchangeRateInput.amount?.times(targetCurrencyPayload ?: 0.0)
                val converAmount = exchangeRateInput.amount * targetCurrencyPayload!!

                return converAmount.let {
                    ExchangeRatePayload(
                        success = exchangeRateResponse.success,
                        timestamp = exchangeRateResponse.timestamp,
                        base = exchangeRateResponse.base,
                        date = exchangeRateResponse.date,
                        rates = exchangeRateResponse.rates,
                        amount = it
                    )
                }
            } else {
                throw Exception("Error getting exchange rates")
            }


        } catch (exception: CustomException) {
            throw exception
        }
    }
}