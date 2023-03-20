package contacts

/**
 * Created with IntelliJ IDEA.
$ Project: Contacts (Kotlin)
 * User: rodrigotroy
 * Date: 20-03-23
 * Time: 10:57
 */
enum class ContactType(val type: String) {
    PERSON("person"),
    ORGANIZATION("organization"),
    UNKNOWN("");

    companion object {
        fun fromString(type: String): ContactType {
            return values().firstOrNull { it.type == type.trim().lowercase() } ?: UNKNOWN
        }
    }
}
