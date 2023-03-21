package contacts

/**
 * Created with IntelliJ IDEA.
$ Project: Contacts (Kotlin)
 * User: rodrigotroy
 * Date: 21-03-23
 * Time: 12:37
 */
enum class Gender(val value: String) {
    MALE("M"),
    FEMALE("F"),
    UNKNOWN("[no data]");

    companion object {
        fun fromString(value: String): Gender {
            return values().firstOrNull { it.value == value.trim().uppercase() } ?: UNKNOWN
        }
    }
}
