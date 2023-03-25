package contacts

import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.datetime.Clock
import java.io.File
import java.text.ParseException
import java.text.SimpleDateFormat

private const val FILE_NAME = "contacts.json"

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
    var selectedContact: Contact? = null

    var mode: Mode = Mode.MENU

    init {
        val file = File(FILE_NAME)
        if (file.exists() && file.length() > 0) {
            val json = file.readText()

            //setLenient(false) is important to avoid parsing errors

            val moshi = Moshi.Builder()
                .addLast(KotlinJsonAdapterFactory())
                .build()

            val type = Types.newParameterizedType(
                List::class.java,
                ContactData::class.java
            )

            val listAdapter = moshi.adapter<List<ContactData?>>(type)
            val contactList = listAdapter.lenient().fromJson(json)

            contactList?.forEach { contact ->
                if (contact != null) {
                    when (contact.type) {
                        ContactType.PERSON -> Person.fromData(contact)
                        ContactType.ORGANIZATION -> Organization.fromData(contact)
                        else -> null
                    }?.let { contacts.add(it) }
                }
            }
        } else {
            file.createNewFile()
        }
    }

    fun addContact() {
        println("Enter the type (person, organization):")
        val type = readln().trim()

        if (type == "person") {
            addPerson()
        } else if (type == "organization") {
            addOrganization()
        } else {
            println("Unknown type")
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

        contacts.add(Organization(name, phoneNumber, address, Clock.System.now(), Clock.System.now()))

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

        contacts.add(Person(name, phoneNumber, surname, birthDate, gender, Clock.System.now(), Clock.System.now()))

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

    fun editContact(index: Int) {
        if (contacts.isEmpty()) {
            println("No records to edit!")
            return
        }

        val contact = contacts[index]

        println("Select a field (${contact.getFields()}]):")
        val field = Field.fromString(readln())

        println("Enter ${field.value}:")
        val value = readln()

        contact.setFieldValue(field, value);
        contact.setEditDate()

        println("Saved")
        println(contact)
    }

    fun editContact(contact: Contact?) {
        if (contact == null) {
            println("No records to edit!")
            return
        }

        if (contacts.isEmpty()) {
            println("No records to edit!")
            return
        }

        println("Select a field (${contact.getFields()}]):")
        val field = Field.fromString(readln())

        println("Enter ${field.value}:")
        val value = readln()

        contact.setFieldValue(field, value);
        contact.setEditDate()

        println("Saved")
        println(contact)
    }

    fun editContact() {
        if (contacts.isEmpty()) {
            println("No records to edit!")
            return
        }

        listContacts()
        println("Select a record:")
        val index = readln().toInt() - 1

        editContact(contacts[index])
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

        selectedContact = contacts[index]
        println(selectedContact)
    }

    // implement search functionality. For this, you can append all of the values from all of the properties and check if this string contains a search request. It should support regular expressions, too! And, of course, it should be case insensitive.
    fun searchContacts() {
        println("Enter search query:")
        val query = readln()

        val regex = Regex(query, RegexOption.IGNORE_CASE)

        contacts.forEachIndexed { index, contact ->
            if (regex.containsMatchIn(contact.toString())) {
                println("${index + 1}. ${contact.getListName()}")
            }
        }
    }

    fun save() {
        val moshi = Moshi.Builder()
            .addLast(KotlinJsonAdapterFactory())
            .build()

        val type = Types.newParameterizedType(
            List::class.java,
            ContactData::class.java
        )

        val listAdapter = moshi.adapter<List<ContactData?>>(type)
        val file = File(FILE_NAME)

        val contactList = contacts.map { it.toData() }

        file.appendText(listAdapter.toJson(contactList))
    }
}
