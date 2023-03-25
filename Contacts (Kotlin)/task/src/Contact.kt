package contacts

import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import java.text.SimpleDateFormat

abstract class Contact(
    name: String,
    phoneNumber: String = "",
    protected var timeCreated: Instant = Clock.System.now(),
    protected var timeLastEdit: Instant = timeCreated,
    private val type: ContactType = ContactType.UNKNOWN
) {
    protected val properties: MutableMap<Field, String> =
        mutableMapOf(Field.NAME to name, Field.PHONE_NUMBER to phoneNumber)
    private val simpleTimeFormat = SimpleDateFormat("HH:mm")
    private val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd")

    abstract fun getFields(): List<Field>

    fun getFieldValue(field: Field): String {
        return properties.getOrDefault(field, "")
    }

    abstract fun setFieldValue(field: Field, value: String)

    abstract fun getListName(): String

    abstract fun toData(): ContactData

    fun isPerson(): Boolean {
        return type == ContactType.PERSON
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
