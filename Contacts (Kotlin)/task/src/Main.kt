package contacts

fun main() {
    val contacts = Contacts()

    while (true) {
        println("Enter action (add, remove, edit, count, info, exit):")
        when (readln().trim()) {
            "add" -> contacts.addContact()
            "remove" -> contacts.removeContact()
            "edit" -> contacts.editContact()
            "count" -> contacts.countContacts()
            "info" -> contacts.infoContact()
            "list" -> contacts.listContacts()
            "exit" -> {
                println("Bye!")
                return
            }

            else -> println("Unknown action")
        }
        println()
    }


}
