package app.megaeventapi.model.dto

data class AddressDTO (
    val street: String,
    val city: String,
    val district: String,
    val number: Int,
    val cep: String
)
