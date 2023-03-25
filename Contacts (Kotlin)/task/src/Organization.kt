package contacts

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
    address: String = ""
) : Contact(name, phoneNumber, ContactType.ORGANIZATION) {
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
}
