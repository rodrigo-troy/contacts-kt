package contacts

data class Contact(
    val name: String,
    val surname: String,
    val phoneNumber: String
) {
    override fun toString(): String {
        return "$name $phoneNumber"
    }
}
