package contacts

/**
 * Created with IntelliJ IDEA.
$ Project: Contacts (Kotlin)
 * User: rodrigotroy
 * Date: 20-03-23
 * Time: 10:54
 */
class Organization(
    name: String,
    phoneNumber: String = "",
    private var address: String = ""
) : Contact(name, phoneNumber, ContactType.ORGANIZATION) {

    fun getAddress(): String {
        return address
    }

    fun setAddress(newAddress: String) {
        address = newAddress
    }

    override fun toString(): String {
        return "Organization name: ${getName()}\n" +
                "Address: $address\n" +
                "Number: ${getPhoneNumber()}\n" +
                "Time created: ${getCreationDate()}\n" +
                "Time last edit: ${getEditDate()}"
    }
}
