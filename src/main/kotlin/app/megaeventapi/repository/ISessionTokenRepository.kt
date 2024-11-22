package app.megaeventapi.repository

import app.megaeventapi.model.entity.SessionToken
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface ISessionTokenRepository: CrudRepository<SessionToken, String> {
    fun findByUserIdAndExpired(userId: String, expired: Boolean): Optional<SessionToken>
    fun findByTokenAndExpired(token: String, expired: Boolean): Optional<SessionToken>
}