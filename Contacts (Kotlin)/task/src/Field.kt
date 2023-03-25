package contacts

/**
 * Created with IntelliJ IDEA.
$ Project: Contacts (Kotlin)
 * User: rodrigotroy
 * Date: 23-03-23
 * Time: 10:57
 */
enum class Field(val value: String) {
    NAME("name"),
    SURNAME("surname"),
    ADDRESS("address"),
    BIRTH_DATE("birth"),
    PHONE_NUMBER("number"),
    GENDER("gender"),
    UNKNOWN("[no data]");

    companion object {
        fun fromString(value: String): Field {
            return Field.values().firstOrNull { it.value == value.trim().uppercase() } ?: Field.UNKNOWN
        }
    }
}
