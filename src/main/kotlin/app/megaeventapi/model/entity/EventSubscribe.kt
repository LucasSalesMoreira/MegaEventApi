package app.megaeventapi.model.entity

import jakarta.persistence.Id
import jakarta.persistence.Entity
import jakarta.persistence.Column
import jakarta.persistence.ManyToOne
import jakarta.persistence.JoinColumn
import java.util.UUID

@Entity(name = "event_subscribe")
class EventSubscribe {
    @Id
    @Column(name = "id")
    var id = UUID.randomUUID().toString()
    @ManyToOne
    @JoinColumn(name = "event_id", referencedColumnName = "id")
    lateinit var event: Event
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    lateinit var user: User

    constructor(event: Event, user: User) {
        this.event = event
        this.user = user
    }

    constructor()
}