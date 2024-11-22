package app.megaeventapi.service

import app.megaeventapi.model.dto.FollowerListDTO
import app.megaeventapi.model.entity.User
import app.megaeventapi.model.form.NetworkForm
import org.springframework.stereotype.Service

@Service
interface INetworkService {
    fun follow(networkForm: NetworkForm, user: User)
    fun listFollowers(followed: User): FollowerListDTO
}
