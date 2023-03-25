package contacts

/**
 * Created with IntelliJ IDEA.
$ Project: Contacts (Kotlin)
 * User: rodrigotroy
 * Date: 20-03-23
 * Time: 11:28
 */
class Person(
    name: String,
    phoneNumber: String = "",
    surname: String = "",
    birthDate: String = "",
    gender: Gender = Gender.UNKNOWN
) : Contact(name, phoneNumber, ContactType.PERSON) {
    init {
        setFieldValue(Field.SURNAME, surname)
        setFieldValue(Field.BIRTH_DATE, birthDate)
        setFieldValue(Field.GENDER, gender.value)
    }

    override fun getFields(): List<Field> {
        return properties.keys.toList() + listOf(Field.SURNAME, Field.BIRTH_DATE, Field.GENDER)
    }


    override fun setFieldValue(field: Field, value: String) {
        properties[field] = value
    }

    override fun getListName(): String {
        return "${getFieldValue(Field.SURNAME)} ${getFieldValue(Field.NAME)}"
    }

    override fun toString(): String {
        return "Name: ${getFieldValue(Field.NAME)}\n" +
                "Surname: ${getFieldValue(Field.SURNAME)}\n" +
                "Birth date: ${getFieldValue(Field.BIRTH_DATE)}\n" +
                "Gender: ${getFieldValue(Field.GENDER)}\n" +
                "Number: ${getFieldValue(Field.PHONE_NUMBER)}\n" +
                "Time created: ${getCreationDate()}\n" +
                "Time last edit: ${getEditDate()}"
    }
}
