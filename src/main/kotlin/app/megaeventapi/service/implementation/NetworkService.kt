package app.megaeventapi.service.implementation

import app.megaeventapi.exception.InvalidFollowedException
import app.megaeventapi.model.dto.FollowerListDTO
import app.megaeventapi.model.dto.FollowerDTO
import app.megaeventapi.model.entity.Network
import app.megaeventapi.model.entity.User
import app.megaeventapi.model.form.NetworkForm
import app.megaeventapi.repository.INetworkRepository
import app.megaeventapi.service.INetworkService
import app.megaeventapi.service.IUserService
import org.springframework.stereotype.Service

@Service
class NetworkService(
    val userService: IUserService,
    val networkRepository: INetworkRepository
): INetworkService {
    override fun follow(networkForm: NetworkForm, user: User) {
        if (networkForm.userId == user.id) {
            throw InvalidFollowedException()
        }
        val userFollowed = userService.findById(networkForm.userId)
        val networkOp = networkRepository
            .findByFollowerAndFollowed(user, userFollowed)
        if (networkOp.isEmpty) {
            val network = Network(
                follower = user,
                followed = userFollowed
            )
            networkRepository.save(network)
        }
    }

    override fun listFollowers(followed: User): FollowerListDTO =
        FollowerListDTO(
            networkRepository.findAllByFollowed(followed).map {
                FollowerDTO(it.follower.id, it.follower.name)
            }
        )
}