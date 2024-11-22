package app.megaeventapi

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class MegaEventApplication

fun main(args: Array<String>) {
	runApplication<MegaEventApplication>(*args)
}