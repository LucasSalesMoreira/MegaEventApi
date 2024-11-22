package app.megaeventapi.service.implementation

import app.megaeventapi.model.entity.SessionToken
import app.megaeventapi.model.entity.User
import app.megaeventapi.repository.ISessionTokenRepository
import app.megaeventapi.service.ISessionTokenService
import app.megaeventapi.util.CryptographyUtil
import org.springframework.stereotype.Service
import java.util.*

@Service
class SessionTokenService(
    private val sessionTokenRepository: ISessionTokenRepository
) : ISessionTokenService {
    override fun findByUserIdAndExpired(userId: String, expired: Boolean): Optional<SessionToken> =
       sessionTokenRepository.findByUserIdAndExpired(userId, expired)

    override fun findByTokenAndExpired(token: String, expired: Boolean): Optional<SessionToken> =
        sessionTokenRepository.findByTokenAndExpired(token, expired)

    override fun configureSessionToken(user: User): String {
        val opSessionTokenActive = sessionTokenRepository.findByUserIdAndExpired(user.id, false)

        if (opSessionTokenActive.isPresent) {
            val sessionTokenActive = opSessionTokenActive.get()
            sessionTokenActive.expired = true
            sessionTokenRepository.save(sessionTokenActive)
        }

        val token = "${user.id}--${UUID.randomUUID()}"
        val tokenEncoded = CryptographyUtil.encodeWithMD5(token)

        val newSessionToken = SessionToken(tokenEncoded, user, false)
        sessionTokenRepository.save(newSessionToken)

        return token
    }
}