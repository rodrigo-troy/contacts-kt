package contacts

data class Contact(
    private var name: String,
    private var surname: String,
    private var phoneNumber: String = ""
) {
    override fun toString(): String {
        return "$name $phoneNumber"
    }

    fun getPhoneNumber(): String {
        return phoneNumber
    }

    fun getFullName(): String {
        return "$name $surname"
    }

    fun setName(newName: String) {
        name = newName
    }

    fun setSurname(newSurname: String) {
        surname = newSurname.ifEmpty {
            "[no number]"
        }
    }

    fun getShortName(): String {
        return "$name ${surname.first()}."
    }

    fun setPhoneNumber(newPhoneNumber: String) {
        phoneNumber = newPhoneNumber
    }

    fun hasNumber(): Boolean {
        return phoneNumber.isNotEmpty()
    }
}
