package app.megaeventapi.repository

import app.megaeventapi.model.entity.Event
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface IEventRepository : CrudRepository<Event, String> {
}