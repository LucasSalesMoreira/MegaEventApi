package app.megaeventapi.model.form

data class AddressForm (
    val street: String,
    val city: String,
    var district: String,
    val number: Int,
    val cep: String
)