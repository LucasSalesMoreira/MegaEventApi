package app.megaeventapi.model.form

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Pattern

data class LoginFormDTO(
//    @field:NotNull(message = "Email não pode ser nulo!")
//    @field:Email(message = "Email inválido!")
    val email: String,

//    @field:NotNull(message = "password não pode ser nulo!")
//    @field:Pattern(
//        regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[\$*&@#])[0-9a-zA-Z\$*&@#]{8,}\$",
//        message = "Senha fraca ou inválida!"
//    )
    val password: String
)