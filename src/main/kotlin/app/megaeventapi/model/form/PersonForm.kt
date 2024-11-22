package app.megaeventapi.model.form

data class PersonForm (
    val name: String,
    val email: String,
    val password: String,
    val address: AddressForm
)
