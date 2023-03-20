package contacts

open class Contact(
    private var name: String,
    private var surname: String,
    private var phoneNumber: String = "",
    private val type: ContactType = ContactType.UNKNOWN
) {
    override fun toString(): String {
        return "$name $phoneNumber"
    }

    fun isPerson(): Boolean {
        return type == ContactType.PERSON
    }

    fun getType(): ContactType {
        return type
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
