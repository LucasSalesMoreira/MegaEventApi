package app.megaeventapi.exception

import app.megaeventapi.model.feedback.Message.USER_EXISTS
import org.springframework.http.HttpStatus

class UserExistsException: RuntimeException(USER_EXISTS) {
    val code = HttpStatus.BAD_REQUEST
}