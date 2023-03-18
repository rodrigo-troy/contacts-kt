package contacts

data class Contact(
    val name: String,
    val surname: String,
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
