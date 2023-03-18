package contacts

fun main() {
    val contacts = Contacts()

    while (true) {
        println("Enter action (add, remove, edit, count, list, exit):")
        val action = readln()
        when (action) {
            "add" -> contacts.addContact()
            "remove" -> contacts.removeContact()
            "edit" -> contacts.editContact()
            "count" -> contacts.countContacts()
            "list" -> contacts.listContacts()
            "exit" -> {
                println("Bye!")
                return
            }

            else -> println("Unknown action")
        }
    }


}
