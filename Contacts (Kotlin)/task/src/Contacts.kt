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
    private val phoneNumberRegex =
        "\\+?((\\([0-9A-Za-z]+\\)|[0-9A-Za-z]+)|([0-9A-Za-z]+[ -]\\([0-9A-Za-z]{2,}\\))|[0-9A-Za-z]+[ -][0-9A-Za-z]{2,})([ -][0-9A-Za-z]{2,}[ -]?)*".toRegex()

    fun addContact() {
        println("Enter the typp:")
        val type = readln()
        println("Enter the name:")
        val name = readln()
        println("Enter the surname:")
        val surname = readln()
        println("Enter the number:")
        var phoneNumber = readln()

        if (!phoneNumberRegex.matches(phoneNumber)) {
            println("Wrong number format!")
            phoneNumber = "[no number]"
        }

        contacts.add(Contact(name, surname, phoneNumber))

        println("The record added.")
    }

    fun removeContact() {
        if (contacts.isEmpty()) {
            println("No records to remove!")
            return
        }

        listContacts()

        println("Select a record:")
        val index = readln().toInt() - 1

        contacts.removeAt(index)

        println("The record removed!")
    }

    fun editContact() {
        if (contacts.isEmpty()) {
            println("No records to edit!")
            return
        }

        println("Select a record:")
        val index = readln().toInt() - 1

        println("Select a field (name, surname, number):")
        val field = readln().trim()

        println("Enter ${field}:")
        var newValue = readln().trim()

        //check if the number is valid using a Regex
        if (field == "number" && !newValue.matches(Regex("\\+?\\d+"))) {
            println("Wrong number format!")
        }

        if (field == "number" && !phoneNumberRegex.matches(newValue)) {
            println("Wrong number format!")
            newValue = "[no number]"
        }

        when (field) {
            "name" -> contacts[index].setName(newValue)
            "surname" -> contacts[index].setSurname(newValue)
            "number" -> contacts[index].setPhoneNumber(newValue)
        }

        println("The record updated!")
    }

    fun countContacts() {
        println("The Phone Book has ${contacts.size} records.")
    }

    fun listContacts() {
        if (contacts.isEmpty()) {
            println("No records to list!")
            return
        }

        println("=== List of records ===")
        contacts.forEachIndexed { index, contact ->
            println("${index + 1}. ${contact.getFullName()}, ${contact.getPhoneNumber()}")
        }

    }
}
