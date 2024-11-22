package app.megaeventapi.model.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import java.util.UUID

@Entity(name = "address")
class Address {
    @Id
    @Column(name = "id")
    val id = UUID.randomUUID().toString()
    @Column(name = "street")
    lateinit var street: String
    @Column(name = "city")
    lateinit var city: String
    @Column(name = "district")
    lateinit var district: String
    @Column(name = "number")
    var number: Int = 0
    @Column(name = "cep")
    lateinit var cep: String

    constructor(street: String, city: String, district: String, number: Int, cep: String) {
        this.street = street
        this.city = city
        this.district = district
        this.number = number
        this.cep = cep
    }

    constructor()
}