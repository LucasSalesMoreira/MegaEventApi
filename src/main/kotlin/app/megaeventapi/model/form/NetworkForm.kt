package app.megaeventapi.model.form

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotEmpty

data class NetworkForm(
    @NotBlank(message = "ID do usuário não pode ser vazio!")
    @NotEmpty(message = "ID do usuário não pode ser vazio!")
    val userId: String
)
