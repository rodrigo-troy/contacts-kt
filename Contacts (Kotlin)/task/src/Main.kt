package contacts

fun main() {
    val contacts = Contacts()

    println("Enter the name of the person:")
    val name = readln()
    println("Enter the surname of the person:")
    val surname = readln()
    println("Enter the number:")
    val phoneNumber = readln()

    val contact = Contact(name, surname, phoneNumber)
    contacts.addContact(contact)

    println("A record created!")
    println("A Phone Book with a single record created!")
}
