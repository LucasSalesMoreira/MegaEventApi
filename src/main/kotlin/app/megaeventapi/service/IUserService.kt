package app.megaeventapi.service

import app.megaeventapi.model.dto.CompanyDTO
import app.megaeventapi.model.dto.PersonDTO
import app.megaeventapi.model.dto.TokenDTO
import app.megaeventapi.model.entity.User
import app.megaeventapi.model.form.CompanyForm
import app.megaeventapi.model.form.LoginFormDTO
import app.megaeventapi.model.form.PersonForm

interface IUserService {
    fun create(personForm: PersonForm): PersonDTO
    fun create(companyForm: CompanyForm): CompanyDTO
    fun login(loginFormDTO: LoginFormDTO): TokenDTO
    fun findById(id: String): User
}