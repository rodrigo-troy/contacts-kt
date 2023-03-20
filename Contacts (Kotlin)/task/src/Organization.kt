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
    phoneNumber: String = ""
) : Contact(name, "", phoneNumber, ContactType.ORGANIZATION)
