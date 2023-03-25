package contacts

/**
 * Created with IntelliJ IDEA.
$ Project: Contacts (Kotlin)
 * User: rodrigotroy
 * Date: 25-03-23
 * Time: 17:56
 */
data class ContactData(
    var type: ContactType = ContactType.UNKNOWN,
    var name: String,
    var phoneNumber: String = "",
    var surname: String = "",
    var birthDate: String = "",
    var gender: String = "",
    var address: String = "",
    var timeCreated: String = "",
    var timeLastEdit: String = ""
)
