package app.megaeventapi.exception

import org.springframework.http.HttpStatus

open class GenericErrorException(message: String, val code: HttpStatus): RuntimeException(message)