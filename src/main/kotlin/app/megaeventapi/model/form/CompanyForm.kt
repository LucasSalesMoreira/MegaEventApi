package app.megaeventapi.model.form

data class CompanyForm(
        val name: String,
        val email: String,
        val cnpj: String,
        val password: String,
        val address: AddressForm
)
