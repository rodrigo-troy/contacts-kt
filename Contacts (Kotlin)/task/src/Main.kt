package contacts

fun main() {
    val contacts = Contacts()

    while (true) {
        println(
            "[${
                contacts.mode.toString().lowercase()
            }] Enter action (${contacts.mode.options.joinToString(separator = ", ") { it.value }}):"
        )
        val option = readln()

        when (contacts.mode) {
            Mode.MENU -> {
                when (Option.fromString(option)) {
                    Option.ADD -> contacts.addContact()
                    Option.LIST -> {
                        contacts.listContacts()
                        contacts.mode = Mode.LIST
                    }

                    Option.SEARCH -> {
                        contacts.searchContacts()
                        contacts.mode = Mode.SEARCH
                    }

                    Option.COUNT -> contacts.countContacts()
                    Option.EXIT -> {
                        contacts.save()
                        return
                    }

                    else -> println("Unknown option")
                }
            }

            Mode.LIST -> {
                when (Option.fromString(option)) {
                    Option.BACK -> contacts.mode = Mode.MENU
                    else -> {
                        contacts.infoContact(option.toInt() - 1)
                        contacts.mode = Mode.RECORD
                    }
                }
            }

            Mode.SEARCH -> {
                when (Option.fromString(option)) {
                    Option.AGAIN -> contacts.searchContacts()
                    Option.BACK -> contacts.mode = Mode.MENU
                    else -> {
                        contacts.infoContact(option.toInt() - 1)
                        contacts.mode = Mode.RECORD
                    }
                }
            }

            Mode.RECORD -> {
                when (Option.fromString(option)) {
                    Option.DELETE -> contacts.removeContact()
                    Option.EDIT -> contacts.editContact()
                    Option.MENU -> contacts.mode = Mode.MENU
                    else -> println("Unknown option")
                }
            }
        }

        println()
    }
}
