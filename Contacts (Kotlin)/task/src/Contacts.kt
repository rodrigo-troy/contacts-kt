package contacts

/**
 * Created with IntelliJ IDEA.
$ Project: Contacts (Kotlin)
 * User: rodrigotroy
 * Date: 18-03-23
 * Time: 19:48
 */
class Contacts {
    private val contacts = mutableListOf<Contact>()

    fun addContact() {
        println("Enter the name of the person:")
        val name = readln()
        println("Enter the surname of the person:")
        val surname = readln()
        println("Enter the number:")
        val phoneNumber = readln()

        contacts.add(Contact(name, surname, phoneNumber))

        println("A record created!")
        println("A Phone Book with a single record created!")

    }

    fun removeContact() {

    }

    fun editContact() {

    }

    fun countContacts() {

    }

    fun listContacts() {

    }
}
