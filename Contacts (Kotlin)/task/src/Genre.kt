package contacts

/**
 * Created with IntelliJ IDEA.
$ Project: Contacts (Kotlin)
 * User: rodrigotroy
 * Date: 21-03-23
 * Time: 12:37
 */
enum class Genre(val value: String) {
    MALE("M"),
    FEMALE("F"),
    UNKNOWN("[no data]");

    companion object {
        fun fromString(value: String): Genre {
            return values().firstOrNull { it.value == value.trim().uppercase() } ?: UNKNOWN
        }
    }
}
