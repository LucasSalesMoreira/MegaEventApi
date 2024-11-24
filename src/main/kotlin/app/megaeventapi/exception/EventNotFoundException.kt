package app.megaeventapi.exception

import app.megaeventapi.model.feedback.Message.EVENT_NOT_FOUND
import org.springframework.http.HttpStatus.NOT_FOUND

class EventNotFoundException: GenericErrorException(EVENT_NOT_FOUND, NOT_FOUND)