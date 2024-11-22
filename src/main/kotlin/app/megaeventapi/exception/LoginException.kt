package app.megaeventapi.exception

import app.megaeventapi.model.feedback.Message.INCORRECT_CREDENTIALS
import org.springframework.http.HttpStatus

class LoginException(
    val code: HttpStatus = HttpStatus.UNAUTHORIZED
): RuntimeException(INCORRECT_CREDENTIALS)