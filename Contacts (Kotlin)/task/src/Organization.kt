package contacts

import kotlinx.datetime.Instant

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
            type = ContactType.ORGANIZATION,
            name = getFieldValue(Field.NAME),
            phoneNumber = getFieldValue(Field.PHONE_NUMBER),
            address = getFieldValue(Field.ADDRESS),
            timeCreated = timeCreated.toString(),
            timeLastEdit = timeLastEdit.toString()
        )
    }

    companion object {
        fun fromData(data: ContactData): Organization {
            return Organization(
                name = data.name,
                phoneNumber = data.phoneNumber,
                address = data.address,
                timeCreated = Instant.parse(data.timeCreated),
                timeLastEdit = Instant.parse(data.timeLastEdit)
            )
        }
    }
}
