package app.megaeventapi.service.implementation

import app.megaeventapi.exception.AlreadySubscribedAtEventException
import app.megaeventapi.exception.EventException
import app.megaeventapi.exception.EventNotFoundException
import app.megaeventapi.mapper.AddressMapper
import app.megaeventapi.mapper.EventMapper
import app.megaeventapi.model.dto.EventDTO
import app.megaeventapi.model.entity.EventSubscribe
import app.megaeventapi.model.entity.User
import app.megaeventapi.model.form.EventForm
import app.megaeventapi.repository.IEventRepository
import app.megaeventapi.repository.IEventSubscribeRepository
import app.megaeventapi.service.IUserService
import app.megaeventapi.service.IEventService
import org.springframework.stereotype.Service

@Service
class EventService(
    private val eventRepository: IEventRepository,
    private val eventSubscribeRepository: IEventSubscribeRepository,
    private val userService: IUserService
) : IEventService {
    override fun create(eventForm: EventForm): EventDTO {
        val mapper = EventMapper()
        val event = mapper.toEvent(eventForm)
        val user = userService.findById(eventForm.owner!!)
        event.owner = user
        return mapper.toEventDTO(eventRepository.save(event))
    }

    override fun update(id: String, eventForm: EventForm): EventDTO {
        val mapper = EventMapper()
        val eventOp = eventRepository.findById(id)
        if (eventOp.isPresent && eventOp.get().owner.id == eventForm.owner) {
            val event = eventOp.get()
            event.name = eventForm.name
            event.dateEvent = eventForm.dateEvent
            event.price = eventForm.price
            event.address = AddressMapper().toAddress(eventForm.address)
            return mapper.toEventDTO(eventRepository.save(event))
        }
        throw EventException()
    }

    override fun list(id: String): List<EventDTO> {
        return eventRepository.findAll()
            .filter { it.owner.id == id }
            .map { EventMapper().toEventDTO(it) }
    }

    override fun delete(id: String, companyId: String) {
        val event = eventRepository.findById(id)
        if (event.get().owner.id == companyId)
            eventRepository.deleteById(id)
        else
            throw EventException()
    }

    override fun subscribe(id: String, user: User) {
        val event = this.eventRepository.findById(id).orElseThrow { EventNotFoundException() }
        if (this.eventSubscribeRepository.findByEventAndUser(event, user).isEmpty) {
            if (event.subsNumber != null) {
                if (this.eventSubscribeRepository.countByEvent(event) < event.subsNumber!!) {
                    this.eventSubscribeRepository.save(EventSubscribe(event, user))
                }
            } else {
                this.eventSubscribeRepository.save(EventSubscribe(event, user))
            }
        } else {
            throw AlreadySubscribedAtEventException()
        }
    }
}