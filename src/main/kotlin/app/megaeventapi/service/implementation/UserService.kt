package app.megaeventapi.service.implementation

import app.megaeventapi.exception.LoginException
import app.megaeventapi.exception.UserExistsException
import app.megaeventapi.exception.UserNotFoundException
import app.megaeventapi.mapper.UserMapper
import app.megaeventapi.model.dto.CompanyDTO
import app.megaeventapi.model.dto.PersonDTO
import app.megaeventapi.model.dto.TokenDTO
import app.megaeventapi.model.entity.User
import app.megaeventapi.model.entity.User.Role.COMPANY
import app.megaeventapi.model.entity.User.Role.PERSON
import app.megaeventapi.model.form.CompanyForm
import app.megaeventapi.model.form.LoginFormDTO
import app.megaeventapi.model.form.PersonForm
import app.megaeventapi.repository.IUserRepository
import app.megaeventapi.service.ISessionTokenService
import app.megaeventapi.service.IUserService
import app.megaeventapi.util.CryptographyUtil
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userRepository: IUserRepository,
    private val sessionTokenService: ISessionTokenService
) : IUserService {
    override fun create(personForm: PersonForm): PersonDTO {
        if (isExists(personForm.email)) {
            throw UserExistsException()
        }
        val mapper = UserMapper()
        val person = mapper.toPerson(personForm)
        return mapper.toPersonDTO(userRepository.save(person))
    }

    override fun create(companyForm: CompanyForm): CompanyDTO {
        if (isExists(companyForm.email)) {
            throw UserExistsException()
        }
        val mapper = UserMapper()
        val company = mapper.toCompany(companyForm)
        return mapper.toCompanyDTO(userRepository.save(company))
    }

    private fun isExists(email: String) = userRepository.findByEmail(email).isPresent

    override fun login(loginFormDTO: LoginFormDTO): TokenDTO {
        val opUser = userRepository.findByEmailAndPassword(loginFormDTO.email,
            CryptographyUtil.encodeWithMD5(loginFormDTO.password)
        )
        if (opUser.isPresent) {
            val user = opUser.get()
            val userMapper = UserMapper()
            return TokenDTO(
                sessionTokenService.configureSessionToken(user),
                when (user.role) {
                    COMPANY -> userMapper.toCompanyDTO(user)
                    PERSON -> userMapper.toPersonDTO(user)
                }
            )
        }
        throw LoginException()
    }

    override fun findById(id: String): User {
        val userOp = userRepository.findById(id)
        if (userOp.isPresent) {
            return userOp.get()
        }
        throw UserNotFoundException()
    }
}