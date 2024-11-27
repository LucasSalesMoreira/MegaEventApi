package app.megaeventapi.controller

import app.megaeventapi.exception.EventException
import app.megaeventapi.exception.EventNotFoundException
import app.megaeventapi.exception.GenericErrorException
import app.megaeventapi.model.dto.ErrorDTO
import app.megaeventapi.model.feedback.Message.DEFAULT_ERROR
import app.megaeventapi.model.form.EventForm
import app.megaeventapi.service.IEventService
import app.megaeventapi.service.ISessionTokenService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/event")
class EventController(
    private val eventService: IEventService,
    sessionTokenService: ISessionTokenService
): AppController(sessionTokenService) {
    @PostMapping
    fun create(@RequestBody eventForm: EventForm, @RequestHeader sessionToken: String) = try {
        if (verifyToken(sessionToken)) {
            eventForm.owner = get(sessionToken).user!!.id
            ResponseEntity.ok(eventService.create(eventForm))
        } else {
            ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null)
        }
    } catch (error: Exception) {
        ResponseEntity.internalServerError().body(ErrorDTO(DEFAULT_ERROR))
    }

    @PatchMapping("/{id}")
    fun update(
        @PathVariable id: String,
        @RequestBody eventForm: EventForm,
        @RequestHeader sessionToken: String
    ) = try {
        if (verifyToken(sessionToken)) {
            eventForm.owner = get(sessionToken).user!!.id
            ResponseEntity.ok(eventService.update(id, eventForm))
        } else {
            ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null)
        }
    } catch (error: EventException) {
        ResponseEntity.status(error.code).body(ErrorDTO(error.message!!))
    } catch (error: Exception) {
        ResponseEntity.internalServerError().body(ErrorDTO(DEFAULT_ERROR))
    }

    @GetMapping("/list")
    fun list(@RequestHeader sessionToken: String) = try {
        if (verifyToken(sessionToken)) {
            ResponseEntity.ok(eventService.list(get(sessionToken).user!!.id))
        } else {
            ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null)
        }
    } catch (error: Exception) {
        ResponseEntity.internalServerError().body(ErrorDTO(DEFAULT_ERROR))
    }

    @GetMapping("/list-all")
    fun listAll(@RequestHeader sessionToken: String) = try {
        if (verifyToken(sessionToken)) {
            ResponseEntity.ok(eventService.listAll(get(sessionToken).user!!.id))
        } else {
            ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null)
        }
    } catch (error: Exception) {
        ResponseEntity.internalServerError().body(ErrorDTO(DEFAULT_ERROR))
    }

    @DeleteMapping("/{id}")
    fun delete(
        @PathVariable id: String,
        @RequestHeader sessionToken: String
    ) = try {
        if (verifyToken(sessionToken)) {
            ResponseEntity.ok(eventService.delete(id, get(sessionToken).user!!.id))
        } else {
            ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null)
        }
    } catch (error: EventException) {
        ResponseEntity.status(error.code).body(ErrorDTO(error.message!!))
    } catch (error: Exception) {
        ResponseEntity.internalServerError().body(ErrorDTO(DEFAULT_ERROR))
    }

    @PostMapping("/subscribe/{id}")
    fun subscribe(
        @PathVariable id: String,
        @RequestHeader sessionToken: String
    ) = try {
        if (verifyToken(sessionToken)) {
            this.eventService.subscribe(id, get(sessionToken).user!!)
            ResponseEntity.noContent().build()
        } else {
            ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null)
        }
    } catch (error: GenericErrorException) {
        ResponseEntity.status(error.code).body(ErrorDTO(error.message!!))
    } catch (error: Exception) {
        ResponseEntity.internalServerError().body(ErrorDTO(DEFAULT_ERROR))
    }
}