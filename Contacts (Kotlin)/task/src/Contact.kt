package contacts

import kotlinx.datetime.Clock
import java.text.SimpleDateFormat

abstract class Contact(
    name: String,
    phoneNumber: String = "",
    private val type: ContactType = ContactType.UNKNOWN
) {
    protected val properties: MutableMap<Field, String> =
        mutableMapOf(Field.NAME to name, Field.PHONE_NUMBER to phoneNumber)
    private val timeCreated = Clock.System.now()
    private var timeLastEdit = timeCreated
    private val simpleTimeFormat = SimpleDateFormat("HH:mm")
    private val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd")

    var name: String
        get() = properties[Field.NAME] ?: ""
        set(value) {
            properties[Field.NAME] = value
        }

    var phoneNumber: String
        get() = properties[Field.PHONE_NUMBER] ?: ""
        set(value) {
            properties[Field.PHONE_NUMBER] = value
        }


    abstract fun getFields(): List<Field>

    abstract fun getFieldValue(field: Field): String

    abstract fun setFieldValue(field: Field, value: String)

    abstract fun getListName(): String

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
