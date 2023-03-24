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

    fun addContact() {
        println("Enter the type (person, organization):")
        val type = readln().trim()

        when (type) {
            "person" -> addPerson()
            "organization" -> addOrganization()
            else -> println("Unknown type")
        }
    }

    private fun addOrganization() {
        println("Enter the organization name:")
        val name = readln()

        println("Enter the address:")
        val address = readln()

        println("Enter the number:")
        var phoneNumber = readln()

        if (!phoneNumberRegex.matches(phoneNumber)) {
            println("Wrong number format!")
            phoneNumber = "[no number]"
        }

        contacts.add(Organization(name, phoneNumber, address))

        println("The record added.")
    }

    private fun addPerson() {
        println("Enter the name:")
        val name = readln()

        println("Enter the surname:")
        val surname = readln()

        println("Enter the birth date:")
        val birthDate = readln().apply {
            if (!dateIsValid(this)) {
                println("Bad birth date!")
            }
        }.ifEmpty { "[no data]" }

        println("Enter the gender (M, F):")
        val gender = readln().run {
            if (!this.matches(genreRegex)) {
                println("Bad gender!")
            }

            Gender.fromString(this)
        }

        println("Enter the number:")
        var phoneNumber = readln()

        if (!phoneNumberRegex.matches(phoneNumber)) {
            println("Wrong number format!")
            phoneNumber = "[no number]"
        }

        contacts.add(Person(name, phoneNumber, surname, birthDate, gender))

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

        listContacts()
        println("Select a record:")
        val index = readln().toInt() - 1

        when (contacts[index].getType()) {
            ContactType.PERSON -> editPerson(index)
            ContactType.ORGANIZATION -> editOrganization(index)
            else -> println("Unknown type")
        }
    }

    private fun editOrganization(index: Int) {
        println("Select a field (name, address, number):")
        val field = readln().trim()

        println("Enter ${field}:")
        var newValue = readln().trim()

        if (field == "number" && !phoneNumberRegex.matches(newValue)) {
            println("Wrong number format!")
            newValue = "[no number]"
        }

        val contact = contacts[index] as Organization

        when (field) {
            "name" -> contact.setName(newValue)
            "address" -> contact.setAddress(newValue)
            "number" -> contact.setPhoneNumber(newValue)
        }

        println("The record updated!")
    }

    private fun editPerson(index: Int) {
        println("Select a field (name, surname, birth, gender, number):")
        val field = readln().trim()

        println("Enter ${field}:")
        var newValue = readln().trim()

        if (field == "number" && !phoneNumberRegex.matches(newValue)) {
            println("Wrong number format!")
            newValue = "[no number]"
        }

        if (field == "birth" && !dateIsValid(newValue)) {
            println("Bad birth date!")
            newValue = "[no data]"
        }

        val contact = contacts[index] as Person

        when (field) {
            "name" -> contact.setName(newValue)
            "surname" -> contact.setSurname(newValue)
            "number" -> contact.setPhoneNumber(newValue)
            "birth" -> contact.setBirthDate(newValue)
            "gender" -> contact.setGenre(Gender.fromString(newValue))
        }

        println("The record updated!")
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
}
