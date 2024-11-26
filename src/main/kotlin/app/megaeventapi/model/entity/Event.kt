package app.megaeventapi.model.entity

import jakarta.persistence.Id
import jakarta.persistence.Entity
import jakarta.persistence.Column
import jakarta.persistence.OneToOne
import jakarta.persistence.JoinColumn
import jakarta.persistence.CascadeType
import java.time.LocalDateTime
import java.util.UUID

@Entity(name = "event")
class Event {

    @Id
    @Column(name = "id")
    var id = UUID.randomUUID().toString()

    @Column(name = "date_event")
    lateinit var dateEvent: LocalDateTime

    @Column(name = "price")
    var price: Double = 0.0
    
    @Column(name = "name")
    lateinit var name: String

    @Column(name = "subs_number")
    var subsNumber: Int? = null

    @OneToOne(cascade = [CascadeType.REMOVE, CascadeType.MERGE, CascadeType.PERSIST])
    @JoinColumn(name = "address_id")
    lateinit var address: Address

    @OneToOne(cascade = [])
    @JoinColumn(name = "owner_id")
    lateinit var owner: User

    constructor(dateEvent: LocalDateTime, price: Double, name: String, subsNumber: Int?, address: Address) {
        this.dateEvent = dateEvent
        this.price = price
        this.name = name
        this.subsNumber = subsNumber
        this.address = address
    }

    constructor()
}