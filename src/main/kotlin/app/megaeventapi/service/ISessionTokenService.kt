package app.megaeventapi.service

import app.megaeventapi.model.entity.SessionToken
import app.megaeventapi.model.entity.User
import org.springframework.stereotype.Service
import java.util.*

@Service
interface ISessionTokenService {
    fun findByUserIdAndExpired(userId: String, expired: Boolean): Optional<SessionToken>
    fun findByTokenAndExpired(token: String, expired: Boolean): Optional<SessionToken>
    fun configureSessionToken(user: User): String
}