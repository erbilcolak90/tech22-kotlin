package tech22solutions.entity.payload



import kotlinx.serialization.Serializable
import tech22solutions.entity.DateSerializer
import java.util.*

@Serializable
data class ExchangeRatePayload(
    var success: Boolean?,
    var timestamp: Long?,
    var base: String,
    @Serializable(with = DateSerializer::class)
    var date: Date?,
    var rates: Map<String, Double>,
    var amount: Double = 0.0
)
