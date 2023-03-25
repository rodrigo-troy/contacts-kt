package contacts

import kotlinx.datetime.Instant
import java.text.SimpleDateFormat

/**
 * Created with IntelliJ IDEA.
$ Project: Contacts (Kotlin)
 * User: rodrigotroy
 * Date: 20-03-23
 * Time: 10:54
 */
class Organization(
    name: String,
    phoneNumber: String = "",
    address: String = "",
    timeCreated: Instant,
    timeLastEdit: Instant
) : Contact(name, phoneNumber, timeCreated, timeLastEdit, ContactType.ORGANIZATION) {
    init {
        setFieldValue(Field.ADDRESS, address)
    }

    override fun getFields(): List<Field> {
        return properties.keys.toList() + Field.ADDRESS
    }

    override fun setFieldValue(field: Field, value: String) {
        properties[field] = value
    }

    override fun getListName(): String {
        return getFieldValue(Field.NAME)
    }

    override fun toString(): String {
        return "Organization name: ${getFieldValue(Field.NAME)}\n" +
                "Address: ${getFieldValue(Field.ADDRESS)}\n" +
                "Number: ${getFieldValue(Field.PHONE_NUMBER)}\n" +
                "Time created: ${getCreationDate()}\n" +
                "Time last edit: ${getEditDate()}"
    }

    override fun toData(): ContactData {
        return ContactData(
            name = getFieldValue(Field.NAME),
            phoneNumber = getFieldValue(Field.PHONE_NUMBER),
            address = getFieldValue(Field.ADDRESS)
        )
    }

    companion object {
        fun fromData(data: ContactData): Organization {
            val simpleTimeFormat = SimpleDateFormat("HH:mm")
            val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd")

            return Organization(
                name = data.name,
                phoneNumber = data.phoneNumber,
                address = data.address,
                timeCreated = Instant.fromEpochMilliseconds(
                    simpleDateFormat.parse(data.timeCreated).time + simpleTimeFormat.parse(
                        data.timeCreated
                    ).time
                ),
                timeLastEdit = Instant.fromEpochMilliseconds(
                    simpleDateFormat.parse(data.timeLastEdit).time + simpleTimeFormat.parse(
                        data.timeLastEdit
                    ).time
                )
            )
        }
    }
}
