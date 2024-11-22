package app.megaeventapi.controller

import app.megaeventapi.exception.InvalidFollowedException
import app.megaeventapi.exception.UserNotFoundException
import app.megaeventapi.model.dto.ErrorDTO
import app.megaeventapi.model.feedback.Message.GENERIC_ERROR
import app.megaeventapi.model.form.NetworkForm
import app.megaeventapi.service.ISessionTokenService
import app.megaeventapi.service.INetworkService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity.badRequest
import org.springframework.http.ResponseEntity.internalServerError
import org.springframework.http.ResponseEntity.noContent
import org.springframework.http.ResponseEntity.status
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/network")
class NetworkController (
    sessionTokenService: ISessionTokenService,
    val networkService: INetworkService
): AppController(sessionTokenService) {
    @PostMapping("/follow")
    fun follow(
        @RequestBody networkForm: NetworkForm,
        @RequestHeader sessionToken: String
    ) = try {
        if (!verifyToken(sessionToken)) {
            status(HttpStatus.UNAUTHORIZED).build<Any>()
        }
        networkService.follow(networkForm, get(sessionToken).user!!)
        noContent().build()
    } catch (e: UserNotFoundException) {
        status(e.code).body(ErrorDTO(e.message!!))
    } catch (e: InvalidFollowedException) {
        badRequest().body(ErrorDTO(e.message!!))
    } catch (e: Exception) {
        internalServerError().body(ErrorDTO(GENERIC_ERROR))
    }

    @GetMapping("/followers")
    fun listFollowers(@RequestHeader sessionToken: String) = try {
        if (!verifyToken(sessionToken)) {
            status(HttpStatus.UNAUTHORIZED).build<Any>()
        }
        networkService.listFollowers(get(sessionToken).user!!)
    } catch (e: Exception) {
        e.printStackTrace()
        internalServerError().body(ErrorDTO(GENERIC_ERROR))
    }
}