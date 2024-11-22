package app.megaeventapi.model.entity

import jakarta.persistence.Id
import jakarta.persistence.Entity
import jakarta.persistence.Column
import jakarta.persistence.ManyToOne
import jakarta.persistence.JoinColumn
import java.util.UUID

@Entity(name = "network")
class Network {
    @Id
    @Column(name = "id")
    var id = UUID.randomUUID().toString()
    @ManyToOne
    @JoinColumn(name = "follower", referencedColumnName = "id")
    lateinit var follower: User
    @ManyToOne
    @JoinColumn(name = "followed", referencedColumnName = "id")
    lateinit var followed: User
    constructor(follower: User, followed: User) {
        this.follower = follower
        this.followed = followed
    }
    constructor()
}