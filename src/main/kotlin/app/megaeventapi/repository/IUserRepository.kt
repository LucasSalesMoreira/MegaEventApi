package app.megaeventapi.repository

import app.megaeventapi.model.entity.User
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface IUserRepository : CrudRepository<User, String> {
    fun findByEmailAndPassword(email: String, password: String): Optional<User>
    fun findByEmail(email: String): Optional<User>
}