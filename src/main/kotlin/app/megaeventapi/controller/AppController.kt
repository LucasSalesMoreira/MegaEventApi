package app.megaeventapi.controller

import app.megaeventapi.service.ISessionTokenService
import app.megaeventapi.util.CryptographyUtil.Companion.encodeWithMD5
import org.springframework.http.ResponseEntity
import org.springframework.validation.BindingResult

open class AppController(private val sessionTokenService: ISessionTokenService) {
    protected fun formatErrors(bindingResult: BindingResult): ResponseEntity<Any> {
        val errors = ArrayList<String>()
        bindingResult.allErrors.forEach {
            errors.add(it.defaultMessage!!)
        }
        return ResponseEntity.badRequest().body(errors)
    }

    protected fun verifyToken(sessionToken: String) = try {
        sessionTokenService
            .findByTokenAndExpired(encodeWithMD5(sessionToken), false)
            .isPresent
    } catch (e: Exception) {
        false
    }

    protected fun get(sessionToken: String) =
        sessionTokenService
            .findByTokenAndExpired(encodeWithMD5(sessionToken), false)
            .get()
}