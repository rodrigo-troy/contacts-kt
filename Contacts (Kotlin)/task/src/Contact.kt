package contacts

import kotlinx.datetime.Clock
import java.text.SimpleDateFormat

open class Contact(
    private var name: String,
    private var phoneNumber: String = "",
    private val type: ContactType = ContactType.UNKNOWN
) {
    private val timeCreated = Clock.System.now()
    private var timeLastEdit = timeCreated
    private val simpleTimeFormat = SimpleDateFormat("HH:mm")
    private val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd")

    open fun getName(): String {
        return name
    }

    fun setPhoneNumber(newPhoneNumber: String) {
        phoneNumber = newPhoneNumber
    }

    fun getPhoneNumber(): String {
        return phoneNumber
    }

    fun setName(newName: String) {
        name = newName
    }

    fun isPerson(): Boolean {
        return type == ContactType.PERSON
    }

    fun getType(): ContactType {
        return type
    }

    fun hasNumber(): Boolean {
        return phoneNumber.isNotEmpty()
    }

    fun getCreationDate(): String {
        return "${simpleDateFormat.format(timeCreated.toEpochMilliseconds())}T${simpleTimeFormat.format(timeCreated.toEpochMilliseconds())}"
    }

    fun getEditDate(): String {
        return "${simpleDateFormat.format(timeLastEdit.toEpochMilliseconds())}T${simpleTimeFormat.format(timeLastEdit.toEpochMilliseconds())}"
    }

    fun setEditDate() {
        timeLastEdit = Clock.System.now()
    }
}
