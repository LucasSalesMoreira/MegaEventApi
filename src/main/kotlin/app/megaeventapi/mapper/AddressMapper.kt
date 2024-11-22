package app.megaeventapi.mapper

import app.megaeventapi.model.dto.AddressDTO
import app.megaeventapi.model.entity.Address
import app.megaeventapi.model.form.AddressForm

class AddressMapper {
    fun toAddress(addressForm: AddressForm) = Address(
        street = addressForm.street,
        city = addressForm.city,
        district = addressForm.district,
        number = addressForm.number,
        cep = addressForm.cep
    )

    fun toAddressDTO(address: Address) = AddressDTO(
        street = address.street,
        city = address.city,
        district = address.district,
        number = address.number,
        cep = address.cep
    )
}