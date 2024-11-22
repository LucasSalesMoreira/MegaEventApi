package app.megaeventapi.exception

import app.megaeventapi.model.feedback.Message.INVALID_FOLLOWED
import org.springframework.http.HttpStatus

class InvalidFollowedException(
    val code: HttpStatus = HttpStatus.BAD_REQUEST
): RuntimeException(INVALID_FOLLOWED)