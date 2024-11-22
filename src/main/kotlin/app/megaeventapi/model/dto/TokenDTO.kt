package app.megaeventapi.model.dto

data class TokenDTO(
    var sessionToken: String,
    var user: Any
)