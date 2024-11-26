package app.megaeventapi.exception

import app.megaeventapi.model.feedback.Message.ALREADY_SUBSCRIBED
import org.springframework.http.HttpStatus.BAD_REQUEST

class AlreadySubscribedAtEventException: GenericErrorException(ALREADY_SUBSCRIBED, BAD_REQUEST)