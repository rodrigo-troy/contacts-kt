package contacts

import kotlinx.datetime.Instant

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
    gender: Gender = Gender.UNKNOWN,
    timeCreated: Instant,
    timeLastEdit: Instant
) : Contact(name, phoneNumber, timeCreated, timeLastEdit, ContactType.PERSON) {
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
        return "${getFieldValue(Field.NAME)} ${getFieldValue(Field.SURNAME)} "
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

    override fun toData(): ContactData {
        return ContactData(
            type = ContactType.PERSON,
            name = getFieldValue(Field.NAME),
            phoneNumber = getFieldValue(Field.PHONE_NUMBER),
            surname = getFieldValue(Field.SURNAME),
            birthDate = getFieldValue(Field.BIRTH_DATE),
            gender = getFieldValue(Field.GENDER),
            timeCreated = timeCreated.toString(),
            timeLastEdit = timeLastEdit.toString()
        )
    }

    companion object {
        fun fromData(data: ContactData): Person {
            return Person(
                name = data.name,
                phoneNumber = data.phoneNumber,
                surname = data.surname,
                birthDate = data.birthDate,
                gender = Gender.fromString(data.gender),
                timeCreated = Instant.parse(data.timeCreated),
                timeLastEdit = Instant.parse(data.timeLastEdit)
            )
        }
    }
}
