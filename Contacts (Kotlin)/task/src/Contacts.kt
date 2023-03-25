package contacts

import java.text.ParseException
import java.text.SimpleDateFormat

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
    private val numberRegex = Regex("\\+?\\d+")
    private val genreRegex = Regex("^(\\t|\\s)*[MF]\$")
    private val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd")

    var mode: Mode = Mode.MENU


    fun addContact() {
        println("Enter the type (person, organization):")
        val type = readln().trim()

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

        listContacts()
        println("Select a record:")
        val index = readln().toInt() - 1

        val contact = contacts[index]

        println("Select a field (${contact.getFields()}]):")
        val field = Field.fromString(readln())

        println("Enter ${field.value}:")
        val value = readln()

        contact.setFieldValue(field, value);

        println("Saved")
        println(contact)
    }

    fun countContacts() {
        println("The Phone Book has ${contacts.size} records.")
    }

    fun listContacts() {
        contacts.forEachIndexed { index, contact ->
            println("${index + 1}. ${contact.getListName()}")
        }
    }

    private fun dateIsValid(date: String): Boolean {
        this.simpleDateFormat.isLenient = false

        return try {
            simpleDateFormat.parse(date)
            true
        } catch (e: ParseException) {
            false
        }
    }

    fun infoContact(index: Int) {
        if (contacts.isEmpty()) {
            println("No records to list!")
            return
        }

        println(contacts[index])
    }

    fun infoContact() {
        if (contacts.isEmpty()) {
            println("No records to list!")
            return
        }

        listContacts()

        println("Enter index to show info:")
        val index = readln().toInt() - 1

        println(contacts[index])
    }

    fun searchContacts() {
        if (contacts.isEmpty()) {
            println("No records to search!")
            return
        }

        println("Enter search query:")
        val query = readln().trim()

        contacts.forEachIndexed { index, contact ->
            if (contact.getListName().contains(query, true)) {
                println("${index + 1}. ${contact.getListName()}")
            }
        }
    }
}
