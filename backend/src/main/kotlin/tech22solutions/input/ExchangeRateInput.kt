package tech22solutions.input

@kotlinx.serialization.Serializable
data class ExchangeRateInput(
    var baseCurrency: String,
    var targetCurrency: String,
    var amount: Double
) {
    fun isValid(): Boolean {
        val currenciesAreDifferent = baseCurrency != targetCurrency
        val amountIsPositive = amount > 0
        return currenciesAreDifferent && amountIsPositive
    }
}