package contacts

/**
 * Created with IntelliJ IDEA.
$ Project: Contacts (Kotlin)
 * User: rodrigotroy
 * Date: 20-03-23
 * Time: 11:28
 */
class Person(
    name: String,
    surname: String,
    phoneNumber: String = ""
) : Contact(name, surname, phoneNumber, ContactType.PERSON)
