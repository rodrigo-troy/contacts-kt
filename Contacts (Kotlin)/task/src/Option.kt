package contacts

/**
 * Created with IntelliJ IDEA.
$ Project: Contacts (Kotlin)
 * User: rodrigotroy
 * Date: 25-03-23
 * Time: 16:13
 */
enum class Option(val value: String) {
    ADD("add"),
    DELETE("delete"),
    EDIT("edit"),
    COUNT("count"),
    INFO("info"),
    LIST("list"),
    SEARCH("search"),
    EXIT("exit"),
    BACK("back"),
    AGAIN("again"),
    NUMBER("[number]"),
    MENU("menu"),
    UNKNOWN("unknown");

    companion object {
        fun fromString(value: String): Option {
            return values().firstOrNull { it.value == value.trim().uppercase() } ?: UNKNOWN
        }
    }
}
