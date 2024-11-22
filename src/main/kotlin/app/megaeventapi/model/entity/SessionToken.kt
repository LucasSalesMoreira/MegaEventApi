package app.megaeventapi.model.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import java.time.LocalDateTime

@Entity(name = "session_token")
class SessionToken {
    @Id
    @Column(name = "token")
    lateinit var token: String
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    var user: User? = null
    @Column(name = "expired")
    var expired: Boolean = false
    @Column(name = "created_at")
    val createdAt: LocalDateTime? = null
    @Column(name = "updated_at")
    val updatedAt: LocalDateTime? = null

    constructor(token: String, user: User, expired: Boolean) {
        this.token = token
        this.user = user
        this.expired = expired
    }

    constructor()
}