package app.megaeventapi.repository

import app.megaeventapi.model.entity.Event
import app.megaeventapi.model.entity.EventSubscribe
import app.megaeventapi.model.entity.User
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface IEventSubscribeRepository: CrudRepository<EventSubscribe, String> {
    fun findByEventAndUser(event: Event, user: User): Optional<EventSubscribe>
    fun countByEvent(event: Event): Int
}