package app.megaeventapi.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class TestApiController {
    @GetMapping("/test")
    fun login() = try {
        ResponseEntity.ok(true)
    } catch (e: Exception) {
        ResponseEntity.status(500).body(e.message)
    }
}