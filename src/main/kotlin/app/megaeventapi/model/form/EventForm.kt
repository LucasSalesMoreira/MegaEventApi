package app.megaeventapi.model.form
import java.time.LocalDateTime

data class EventForm(
        val dateEvent: LocalDateTime,
        val price: Double,
        val name: String,
        val subsNumber: Int?,
        val address: AddressForm,
        var owner: String?
)
