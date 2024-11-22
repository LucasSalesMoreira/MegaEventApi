package app.megaeventapi.model.entity

import app.megaeventapi.model.entity.User.Role.COMPANY
import app.megaeventapi.model.entity.User.Role.PERSON
import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.OneToOne

import java.util.UUID

@Entity (name = "user")
class User {
    @Id
    @Column(name = "id")
    val id = UUID.randomUUID().toString()

    @Column(name = "name")
    lateinit var name: String

    @Column(name = "email")
    lateinit var email: String

    @Column(name = "role")
    lateinit var role: Role

    @Column(name = "cnpj")
    var cnpj: String? = null

    @Column(name = "password")
    lateinit var password: String

    @OneToOne(cascade = [CascadeType.REMOVE, CascadeType.MERGE, CascadeType.PERSIST])
    @JoinColumn(name = "address_id")
    lateinit var address: Address

    enum class Role {
        COMPANY,
        PERSON
    }
    
    constructor(name: String, email: String, cnpj: String, password: String, address: Address) {
        this.name = name
        this.email = email
        this.cnpj = cnpj
        this.password = password
        this.address = address
        this.role = COMPANY
    }

    constructor(name: String, email: String, password: String, address: Address) {
        this.name = name
        this.email = email
        this.password = password
        this.address = address
        this.role = PERSON
    }

    constructor()
}