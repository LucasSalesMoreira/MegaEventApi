package app.megaeventapi.service

import app.megaeventapi.model.dto.EventDTO
import app.megaeventapi.model.form.EventForm

interface IEventService {

    fun create(eventForm: EventForm): EventDTO
    fun update(id: String, eventForm: EventForm): EventDTO
    fun list(id: String): List<EventDTO>
    fun delete(id: String, companyId: String)
}